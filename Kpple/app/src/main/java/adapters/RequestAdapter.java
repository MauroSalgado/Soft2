package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import co.edu.konranlorenz.kpple.R;
import entities.Request;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.MyViewHolder> {
    private Context mContext;
    private List<Request> reqList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, txtTypeReq;
        public ImageView profileCard;
        public Button btnAcceptReq, btnRejectReq;

        public MyViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.card_textview_name);
            profileCard = itemView.findViewById(R.id.imagecardperson);
            txtTypeReq = itemView.findViewById(R.id.txtTypeReq);
            btnAcceptReq = itemView.findViewById(R.id.btnAccept);
            btnRejectReq= itemView.findViewById(R.id.btnReject);
        }
    }

    public RequestAdapter(Context mContext, List<Request> reqList) {
        this.mContext = mContext;
        this.reqList = reqList;
    }

    public void setData(List<Request> newPost) {
        this.reqList.clear();
        reqList.addAll(newPost);
        notifyDataSetChanged();
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
        final Request reqFriend = reqList.get(position);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final String userID = mAuth.getCurrentUser().getUid();
        final String friendID = reqFriend.getId();
        final DatabaseReference friend = FirebaseDatabase.getInstance().getReference("User/" + friendID);
        final DatabaseReference reqRef = FirebaseDatabase.getInstance().getReference("Friendship/" + userID+"/"+friendID);

        reqRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dsReqRef) {
                if (dsReqRef.exists()) {
                    friend.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dsFriend) {
                            if (dsReqRef.child("flag").getValue().toString().equals("No")){
                                holder.userName.setText(dsFriend.child("name").getValue().toString());
                                if (reqFriend.getTypeReq().equals("1")){
                                    holder.txtTypeReq.setText("Friendship Request");
                                }else{
                                    holder.txtTypeReq.setText("Couple Request");
                                }

                                Glide.with(mContext)
                                        .load(dsFriend.child("urlImgProfile").getValue().toString())
                                        .into(holder.profileCard);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        holder.btnAcceptReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reqRef.child("flag").setValue("Yes");
                setData(reqList);
            }
        });

        holder.btnRejectReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reqRef.child(friendID).removeValue();
                setData(reqList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reqList.size();
    }


}
