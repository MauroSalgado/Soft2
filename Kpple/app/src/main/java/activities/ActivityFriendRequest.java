package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;

import co.edu.konranlorenz.kpple.R;
import co.edu.konranlorenz.kpple.TabPrincipalController;
import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityFriendRequest extends AppCompatActivity {

    private TextView name;
    private CircleImageView imgProfile;
    private Button btnRequest;

    private DatabaseReference friendRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
        Intent friendIntent = getIntent();
        Bundle extras = friendIntent.getExtras();
        name = findViewById(R.id.txtNameReq);
        imgProfile = findViewById(R.id.imgFriendReq);
        btnRequest = findViewById(R.id.btnSendRequest);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendRef = FirebaseDatabase.getInstance().getReference("Friendship");
                FirebaseUser user = mAuth.getCurrentUser();
                friendRef.child(extras.getString("ID")).child(user.getUid()).setValue("No");
                FancyToast.makeText(getBaseContext(), "Request send succesfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,true).show();
                startActivity(new Intent(getBaseContext(), TabPrincipalController.class));
                finish();
            }
        });

        name.setText(extras.getString("NAME"));
        Glide.with(getBaseContext())
                .load(extras.getString("URL_IMG"))
                .into(imgProfile);

    }
}
