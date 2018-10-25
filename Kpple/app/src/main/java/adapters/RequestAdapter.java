package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import co.edu.konranlorenz.kpple.R;
import entities.User;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.MyViewHolder> {
    private Context mContext;
    private List<User> userList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, post_values, likes;
        public ImageView profileCard;
        public MyViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.card_textview_name);
            post_values = itemView.findViewById(R.id.card_text_post_values);
            likes = itemView.findViewById(R.id.card_text_like_values);
            profileCard = itemView.findViewById(R.id.imagecardperson);
        }
    }

    public RequestAdapter(Context mContext, List<User> userList) {
        this.mContext = mContext;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.request_person_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = userList.get(position);
        holder.userName.setText(user.getName());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


}
