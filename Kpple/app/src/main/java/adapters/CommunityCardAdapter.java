package adapters;

import android.content.Context;
import android.content.Intent;
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

import co.edu.konranlorenz.kpple.FriendProfileController;
import co.edu.konranlorenz.kpple.R;
import entities.Community;

public class CommunityCardAdapter extends RecyclerView.Adapter<CommunityCardAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Community> mComunity;
    private Context context;

    public CommunityCardAdapter(Context mContext, List<Community> Comunity, Context context) {
        this.mContext = context;
        this.mComunity = Comunity;
        this.context = context;
    }


    public void setData(List<Community> newCommunity) {
        this.mComunity.clear();
        mComunity.addAll(newCommunity);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommunityCardAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.home_person_item, parent, false);
        return new CommunityCardAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, int position) {
        final Community communityCurrent = mComunity.get(position);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String url = "Post/" + communityCurrent.getIdComunity();
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


        StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mStorageRef.getDownloadUrl();
        holder.imgCardPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, FriendProfileController.class);
                intent.putExtra("Nombre", communityCurrent.getIdComunity());
                context.startActivity(intent);

            }
        });
        Glide.with(mContext)
                .load(communityCurrent.getUrlImage())
                .into(holder.imgCardPerson);
    }


    @Override
    public int getItemCount() {
        return mComunity.size();
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
