package adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

import java.util.List;

import activities.ActivityFriendRequest;
import co.edu.konranlorenz.kpple.FriendProfileController;
import co.edu.konranlorenz.kpple.R;
import entities.User;

public class PersonCardAdapter extends RecyclerView.Adapter<PersonCardAdapter.ImageViewHolder> {
    private Context mContext;
    private List<User> mUser;
    private Context context;

    public PersonCardAdapter(Context mContext, List<User> mUser, Context context) {
        this.mContext = mContext;
        this.mUser = mUser;
        this.context = context;
    }


    public void setData(List<User> newUser) {
        this.mUser.clear();
        mUser.addAll(newUser);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.home_person_item, parent, false);
        return new ImageViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, int position) {
        final User userCurrent = mUser.get(position);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();


        String url = "Post/" + userCurrent.getIdUser();
        final DatabaseReference refUser = FirebaseDatabase.getInstance().getReference("User");

        // [END initialize_auth]

        // comienza el método de likes
        if (mAuth != null) {
            final DatabaseReference refPost = FirebaseDatabase.getInstance().getReference(url);
            refPost.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int result = 0;
                    long counter = 0;
                    if (dataSnapshot.exists()) {

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String likes = ds.child("like").getValue().toString();
                            int likesint = Integer.parseInt(likes);
                            result = result + likesint;
                            counter = dataSnapshot.getChildrenCount();
                            Log.i("SplashAct", "" + result);
                        }
                    }
                    holder.txtlikes.setText("" + result);
                    holder.txtposts.setText("" + counter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        // termina el método de like

        holder.txtName.setText(userCurrent.getName());
        StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mStorageRef.getDownloadUrl();
        holder.imgCardPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user = mAuth.getCurrentUser().getUid();
                final DatabaseReference refFriend = FirebaseDatabase.getInstance().getReference("Friendship/" + user);
                refFriend.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Bundle extras = new Bundle();
                        extras.putString("ID", userCurrent.getIdUser());
                        extras.putString("NAME", userCurrent.getName());
                        extras.putString("URL_IMG", userCurrent.getUrlImgProfile());
                        Intent friendIntent = new Intent(context, ActivityFriendRequest.class);
                        friendIntent.putExtras(extras);
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                if (ds.getChildren().toString().equals(userCurrent.getIdUser())) {
                                    if (ds.child(userCurrent.getIdUser()).getValue().toString().equals("Yes")) {
                                        Intent intent = new Intent(context, FriendProfileController.class);
                                        intent.putExtra("iduser", userCurrent.getIdUser());
                                        context.startActivity(intent);
                                    }else{
                                        context.startActivity(friendIntent);
                                    }
                                }
                            }
                        } else {
                            context.startActivity(friendIntent);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                /**/

            }
        });
        Glide.with(mContext)
                .load(userCurrent.getUrlImgProfile())
                .into(holder.imgCardPerson);
    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView txtlikes;
        public TextView txtposts;
        public TextView txtName;
        public ImageView imgCardPerson;

        public ImageViewHolder(View itemView) {
            super(itemView);
            txtlikes = itemView.findViewById(R.id.card_text_like_values);
            txtposts = itemView.findViewById(R.id.card_text_post_values);
            txtName = itemView.findViewById(R.id.card_textview_name);
            imgCardPerson = itemView.findViewById(R.id.imagecardperson);
        }
    }
}

