package adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Post postCurrent = mPosts.get(position);
        holder.txtPost.setText(postCurrent.getTxtPost());
        StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mStorageRef.getDownloadUrl();
        Glide.with(mContext)
                .load("https://firebasestorage.googleapis.com/v0/b/ingsoft2-65cc5.appspot.com/o/KELBCmoUSRYlh3bAvIeo7RXDSm82%2Fpost%2Fpareja.jpg?alt=media&token=58509006-7145-4720-ae0d-5bb0d6852f32")
                //.load(R.drawable.logo)
                //.load(postCurrent.getUrlImage())
                .into(holder.imgPost);
        /*Picasso.with(mContext)
                //.load(R.drawable.logo)
                .load(postCurrent.getUrlImage())
                .fit()
                .centerCrop()
                .into(holder.imgPost);*/
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView txtPost;
        public ImageView imgPost;

        public ImageViewHolder(View itemView) {
            super(itemView);
            txtPost = itemView.findViewById(R.id.txtPost);
            imgPost = itemView.findViewById(R.id.imgPost);
        }
    }
}
