package activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.konranlorenz.kpple.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class HolderMessage extends RecyclerView.ViewHolder {


    //My message items
    private TextView txtMyMsgBody;
    private TextView txtMyMsgTime;
    private CircleImageView imgMyMsg;
    private ImageView pictureMsg;

    //Their message items
    private TextView txtTheirMsgName;
    private TextView txtTheirMsgBody;
    private TextView txtTheirMsgTime;
    private CircleImageView imgTheirMsg;
    private ImageView imgTheirPicture;

    public HolderMessage(View itemView) {
        super(itemView);
        txtMyMsgBody = (TextView) itemView.findViewById(R.id.txtMyMsgBody);
        txtMyMsgTime = (TextView) itemView.findViewById(R.id.txtMyMsgTime);
        imgMyMsg = (CircleImageView) itemView.findViewById(R.id.imgMyMsg);
        pictureMsg = (ImageView) itemView.findViewById(R.id.pictureMsg);

        txtTheirMsgName = (TextView) itemView.findViewById(R.id.txtTheirMsgName);
        txtTheirMsgBody = (TextView) itemView.findViewById(R.id.txtTheirMsgBody);
        txtTheirMsgTime = (TextView) itemView.findViewById(R.id.txtTheirMsgTime);
        imgTheirMsg = (CircleImageView) itemView.findViewById(R.id.imgTheirMsg);
        imgTheirPicture = (ImageView) itemView.findViewById(R.id.imgTheirPicture);

    }

    public TextView getTxtMyMsgBody() {
        return txtMyMsgBody;
    }

    public void setTxtMyMsgBody(TextView txtMyMsgBody) {
        this.txtMyMsgBody = txtMyMsgBody;
    }

    public TextView getTxtMyMsgTime() {
        return txtMyMsgTime;
    }

    public void setTxtMyMsgTime(TextView txtMyMsgTime) {
        this.txtMyMsgTime = txtMyMsgTime;
    }

    public CircleImageView getImgMyMsg() {
        return imgMyMsg;
    }

    public void setImgMyMsg(CircleImageView imgMyMsg) {
        this.imgMyMsg = imgMyMsg;
    }

    public ImageView getPictureMsg() {
        return pictureMsg;
    }

    public void setPictureMsg(ImageView pictureMsg) {
        this.pictureMsg = pictureMsg;
    }

    public TextView getTxtTheirMsgName() {
        return txtTheirMsgName;
    }

    public void setTxtTheirMsgName(TextView txtTheirMsgName) {
        this.txtTheirMsgName = txtTheirMsgName;
    }

    public TextView getTxtTheirMsgBody() {
        return txtTheirMsgBody;
    }

    public void setTxtTheirMsgBody(TextView txtTheirMsgBody) {
        this.txtTheirMsgBody = txtTheirMsgBody;
    }

    public TextView getTxtTheirMsgTime() {
        return txtTheirMsgTime;
    }

    public void setTxtTheirMsgTime(TextView txtTheirMsgTime) {
        this.txtTheirMsgTime = txtTheirMsgTime;
    }

    public CircleImageView getImgTheirMsg() {
        return imgTheirMsg;
    }

    public void setImgTheirMsg(CircleImageView imgTheirMsg) {
        this.imgTheirMsg = imgTheirMsg;
    }

    public ImageView getImgTheirPicture() {
        return imgTheirPicture;
    }

    public void setImgTheirPicture(ImageView imgTheirPicture) {
        this.imgTheirPicture = imgTheirPicture;
    }
}
