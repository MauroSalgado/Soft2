package activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.edu.konranlorenz.kpple.R;

public class ProfileActivity extends AppCompatActivity {

    ImageView post, gallery, imgProfile;
    TextView txtDisplayName;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();

        PostFragment fragment1 = new PostFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.profile_fragment_container, fragment1);

        post = (ImageView) findViewById(R.id.img_profile_post);
        gallery = (ImageView) findViewById(R.id.img_profile_gallery);
        imgProfile = (ImageView) findViewById(R.id.imgProfile);
        txtDisplayName = (TextView) findViewById(R.id.txtDisplayName);

        loadUserInformation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private void loadUserInformation() {
        final FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            if (user.getPhotoUrl().toString() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        .into(imgProfile);
            }
            if (user.getDisplayName() != null) {
                txtDisplayName.setText(user.getDisplayName());
            }
        }
    }
}
