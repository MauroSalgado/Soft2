package activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.konranlorenz.kpple.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class HolderComment extends RecyclerView.ViewHolder {

    private TextView mTxt_nombre;
    private TextView mTxt_comment;
    private TextView mHora;
    private CircleImageView mFotoMensajePerfil;
    private ImageView mFotoMensaje;

    public HolderComment(View itemView) {
        super(itemView);
        mTxt_nombre = (TextView) itemView.findViewById(R.id.textVw_card_nombre);
        mTxt_comment = (TextView) itemView.findViewById(R.id.textVw_card_comment);
        mHora = (TextView) itemView.findViewById(R.id.texVw_card_hora);
        mFotoMensajePerfil = (CircleImageView) itemView.findViewById(R.id.foto_perfil_comment);
        mFotoMensaje = (ImageView) itemView.findViewById(R.id.img_mensaje_foto);
    }



    public TextView getmTxt_nombre() {
        return mTxt_nombre;
    }

    public void setmTxt_nombre(TextView mTxt_nombre) {
        this.mTxt_nombre = mTxt_nombre;
    }

    public TextView getmTxt_comment() {
        return mTxt_comment;
    }

    public void setmTxt_comment(TextView mTxt_comment) {
        this.mTxt_comment = mTxt_comment;
    }

    public TextView getmHora() {
        return mHora;
    }

    public void setmHora(TextView mHora) {
        this.mHora = mHora;
    }

    public CircleImageView getmFotoMensajePerfil() {
        return mFotoMensajePerfil;
    }

    public void setmFotoMensajePerfil(CircleImageView mFotoMensajePerfil) {
        this.mFotoMensajePerfil = mFotoMensajePerfil;
    }

    public ImageView getmFotoMensaje() {
        return mFotoMensaje;
    }

    public void setmFotoMensaje(ImageView mFotoMensaje) {
        this.mFotoMensaje = mFotoMensaje;
    }
}
