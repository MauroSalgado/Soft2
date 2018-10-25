package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import activities.HolderComment;
import co.edu.konranlorenz.kpple.R;
import entities.Comment;

public class CommentCardAdapter extends RecyclerView.Adapter<HolderComment> {

    private List<Comment>listMensaje=new ArrayList<>();
    private Context c;

    public CommentCardAdapter(Context c) {
        this.c = c;
    }

    public void addComment(Comment comment){

        this.listMensaje.add(comment);
        notifyItemInserted(listMensaje.size());
    }

    @NonNull
    @Override
    public HolderComment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(c).inflate(R.layout.cardview_comment,parent,false);
        return new HolderComment(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderComment holder, int position) {
        holder.getmTxt_nombre().setText(listMensaje.get(position).getNombre());
        holder.getmTxt_comment().setText(listMensaje.get(position).getComment());
        holder.getmHora().setText(listMensaje.get(position).getHora());

    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }
}
