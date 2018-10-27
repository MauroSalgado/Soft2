package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import activities.CommentRecibir;
import activities.HolderComment;
import co.edu.konranlorenz.kpple.R;
import lib.FirebaseFunctions;

public class CommentCardAdapter extends RecyclerView.Adapter<HolderComment> {

    private List<CommentRecibir> listComment =new ArrayList<>();
    private Context c;

    public CommentCardAdapter(Context c) {
        this.c = c;
    }

    public void addComment(CommentRecibir comment){

        this.listComment.add(comment);
        notifyItemInserted(listComment.size());
    }

    @NonNull
    @Override
    public HolderComment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(c).inflate(R.layout.cardview_comment,parent,false);
        return new HolderComment(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderComment holder, int position) {

        holder.getmTxt_nombre().setText(listComment.get(position).getNombre());
        holder.getmTxt_comment().setText(listComment.get(position).getComment());

        if(listComment.get(position).getType_message().equals("2")){
            holder.getmFotoMensaje().setVisibility(View.VISIBLE);
            holder.getmTxt_comment().setVisibility(View.VISIBLE);
            Glide.with(c).load(listComment.get(position).getUrlFoto()).into(holder.getmFotoMensaje());
        }else{
            holder.getmFotoMensaje().setVisibility(View.GONE);
            holder.getmTxt_comment().setVisibility(View.VISIBLE);
        }

        if(listComment.get(position).getFotoPerfil().isEmpty()){
            holder.getmFotoMensajePerfil().setImageResource(R.mipmap.ic_launcher);
        }else {
            Glide.with(c).load(listComment.get(position)).into(holder.getmFotoMensajePerfil());
        }

        Long codigohora = listComment.get(position).getHora();
        Date d = new Date(codigohora);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        holder.getmHora().setText(sdf.format(d));
    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }
}
