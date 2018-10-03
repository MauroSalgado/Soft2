package activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.konranlorenz.kpple.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class HolderMesage extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView message;
    private TextView time;
    private CircleImageView photoMessgProf;
    private ImageView pictureMsg;

    public HolderMesage(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.txtCardName);
    }
}
