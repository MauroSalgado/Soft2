package adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import co.edu.konranlorenz.kpple.R;
import entities.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Post> mPosts;

    public PostAdapter(Context mContext, List<Post> mPosts) {
        this.mContext = mContext;
        this.mPosts = mPosts;
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
        final DatabaseReference refPost = FirebaseDatabase.getInstance().getReference("Post");
        final String user = mAuth.getCurrentUser().getUid();
        final String postID = postCurrent.getIdPost();

        holder.txtUserName.setText(mAuth.getCurrentUser().getDisplayName());
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
                int addLike = postCurrent.getLike()+1;
                refPost.child(user).child(postID).child("like").setValue(addLike);
                holder.txtLike.setText(String.valueOf(addLike));
            }
        });
        holder.imgDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int addDislike = postCurrent.getDislike()+1;
                refPost.child(user).child(postID).child("dislike").setValue(addDislike);
                holder.txtDislike.setText(String.valueOf(addDislike));
            }
        });
        Glide.with(mContext)
                .load(postCurrent.getUrlImage())
                .into(holder.imgPost);
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

        public ImageViewHolder(View itemView) {
            super(itemView);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtPost = itemView.findViewById(R.id.txtPost);
            txtLike = itemView.findViewById(R.id.txtNumLikes);
            txtDislike = itemView.findViewById(R.id.txtNumDislike);
            imgPost = itemView.findViewById(R.id.imgPost);
            imgLike = itemView.findViewById(R.id.imgLike);
            imgDislike = itemView.findViewById(R.id.imgDislike);
        }
    }
}
