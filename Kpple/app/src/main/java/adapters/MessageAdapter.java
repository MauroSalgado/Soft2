package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import activities.HolderMessage;
import entities.MessageGet;
import co.edu.konranlorenz.kpple.R;

public class MessageAdapter extends RecyclerView.Adapter<HolderMessage>  {

    private List<MessageGet> listMessage = new ArrayList<>();
    private Context context;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    public void addMensaje(MessageGet m){
        listMessage.add(m);
        notifyItemInserted(listMessage.size());
    }

    @NonNull
    @Override
    public HolderMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.their_message,parent,false);
        return new HolderMessage(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessage holder, int position) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String urlPhoto = user.getPhotoUrl().toString();
        Glide.with(context).load(urlPhoto).into(holder.getImgTheirMsg());
        holder.getTxtTheirMsgName().setText(listMessage.get(position).getName());
        holder.getTxtTheirMsgBody().setText(listMessage.get(position).getMessage());
        String typeMsg = listMessage.get(position).getType_message();
        if (typeMsg.equals("2")){
            holder.getImgTheirPicture().setVisibility(View.VISIBLE);
            holder.getTxtTheirMsgBody ().setVisibility(View.VISIBLE);
            Glide.with(context).load(listMessage.get(position).getUrlPhoto()).into(holder.getImgTheirPicture());
        }else if (typeMsg.equals("1")){
            holder.getImgTheirPicture().setVisibility(View.GONE);
            holder.getTxtTheirMsgBody().setVisibility(View.VISIBLE);
        }

        Long codeTime = listMessage.get(position).getTime();
        Date date = new Date(codeTime);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        holder.getTxtTheirMsgTime().setText(sdf.format(date));
    }

    @Override
    public int getItemCount() {
        return listMessage.size();
    }
}
