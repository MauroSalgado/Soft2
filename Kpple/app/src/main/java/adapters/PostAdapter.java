package adapters;


import android.animation.Animator;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Date;
import java.util.List;


import activities.PostViewer;
import activities.PostViewerFragment;
import activities.CommentListActivity;
import co.edu.konranlorenz.kpple.R;
import co.edu.konranlorenz.kpple.TabPrincipalController;
import entities.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ImageViewHolder> {

    public static final int NOTIFI_ID =1001;
    public static final String NOTIF_MENSAJE = "NOTIF_MENSAJE";
    private Context mContext;
    private List<Post> mPosts;

    public PostAdapter(Context mContext, List<Post> mPosts) {
        this.mContext = mContext;
        this.mPosts = mPosts;
    }

    public void setData(List<Post> newPost){
        this.mPosts.clear();
        mPosts.addAll(newPost);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.post_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, int position) {

        final Post postCurrent = mPosts.get(position);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("User/"+postCurrent.getIdUser());
        final DatabaseReference refPost = FirebaseDatabase.getInstance().getReference("Post");
        final String user = mAuth.getCurrentUser().getUid();
        final String postID = postCurrent.getIdPost();

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                holder.txtUserName.setText(dataSnapshot.child("name").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        holder.txtPost.setText(postCurrent.getTxtPost());
        String like = Integer.toString(postCurrent.getLike());
        holder.txtLike.setText(like);
        String dislike = Integer.toString(postCurrent.getDislike());
        holder.txtDislike.setText(dislike);

        StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mStorageRef.getDownloadUrl();
        holder.imgPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!postCurrent.getUrlVideo().isEmpty()) {
                    final Snackbar sbVideo = Snackbar.make(view, "Go to the video", Snackbar.LENGTH_SHORT);
                    sbVideo.getView().setBackgroundColor(ContextCompat.getColor((view.getContext()), R.color.colorPrimary));
                    sbVideo.setActionTextColor(view.getResources().getColor(R.color.white));
                    sbVideo.show();
                    sbVideo.setAction("GO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Uri uri = Uri.parse(postCurrent.getUrlVideo());
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            view.getContext().startActivity(intent);
                        }
                    });
                }
            }
        });
        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int addLike = postCurrent.getLike() + 1;
                refPost.child(postCurrent.getIdUser()).child(postID).child("like").setValue(addLike);
                holder.txtLike.setText(String.valueOf(addLike));
                setData(mPosts);
                sendNotificationLike();
            }
        });
        holder.imgDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int addDislike = postCurrent.getDislike() + 1;
                refPost.child(user).child(postID).child("dislike").setValue(addDislike);
                holder.txtDislike.setText(String.valueOf(addDislike));
                setData(mPosts);
                sendNotificationDesLike();
            }
        });

        holder.imgComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommentListActivity.class);
                mContext.startActivity(intent);
            }
        });

        Glide.with(mContext)
                .load(postCurrent.getUrlImage())
                .into(holder.imgPost);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewAttachedToWindow(ImageViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        animateCircularReveal(viewHolder.itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }
    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView txtUserName;
        public TextView txtPost;
        public TextView txtLike;
        public TextView txtDislike;
        public ImageView imgPost;
        public ImageView imgLike;
        public ImageView imgDislike;
        public ImageView imgComment;

        public ImageViewHolder(View itemView) {
            super(itemView);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtPost = itemView.findViewById(R.id.txtPost);
            txtLike = itemView.findViewById(R.id.txtNumLikes);
            txtDislike = itemView.findViewById(R.id.txtNumDislike);
            imgPost = itemView.findViewById(R.id.imgPost);
            imgLike = itemView.findViewById(R.id.imgLike);
            imgDislike = itemView.findViewById(R.id.imgDislike);
            imgComment = itemView.findViewById(R.id.imgComment);
        }
    }

    private void sendNotificationLike(){
        Intent intent = new Intent(mContext, PostViewerFragment.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        int icon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? R.mipmap.ic_launcher : R.mipmap.ic_launcher;
        NotificationManager notificationManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "default";
            NotificationChannel channel = new NotificationChannel(channelId, mContext.getString(R.string.notif_title), NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(mContext.getString(R.string.notif_body));
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            channel.setSound(defaultSoundUri, null);
            channel.setShowBadge(true);
            notificationManager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(mContext, channelId)
                    .setContentTitle(mContext.getString(R.string.notif_title))
                    .setContentText(mContext.getString(R.string.notif_body))
                    .setSmallIcon(icon)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .build();
            notificationManager.notify(m, notification);
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext)
                    .setSmallIcon(icon)
                    .setContentTitle(mContext.getString(R.string.notif_title))
                    .setContentText(mContext.getString(R.string.notif_body))
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)
                    .setLights(Color.BLUE, 3000, 3000);
            notificationManager.notify(m, notificationBuilder.build());
        }

    }

    private void sendNotificationDesLike() {
        Intent intent = new Intent(mContext, PostViewerFragment.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        int icon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? R.mipmap.ic_launcher : R.mipmap.ic_launcher;
        NotificationManager notificationManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "default";
            NotificationChannel channel = new NotificationChannel(channelId, mContext.getString(R.string.notif_title), NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(mContext.getString(R.string.notif_body));
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            channel.setSound(defaultSoundUri, null);
            channel.setShowBadge(true);
            notificationManager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(mContext, channelId)
                    .setContentTitle(mContext.getString(R.string.notif_titleD))
                    .setContentText(mContext.getString(R.string.notif_bodyD))
                    .setSmallIcon(icon)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .build();
            notificationManager.notify(m, notification);
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext)
                    .setSmallIcon(icon)
                    .setContentTitle(mContext.getString(R.string.notif_title))
                    .setContentText(mContext.getString(R.string.notif_body))
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)
                    .setLights(Color.BLUE, 3000, 3000);
            notificationManager.notify(m, notificationBuilder.build());
        }
    }
}
