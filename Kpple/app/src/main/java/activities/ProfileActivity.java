package activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import co.edu.konranlorenz.kpple.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView post, gallery, info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        PostFragment fragment1 = new PostFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.profile_fragment_container,fragment1);

        post = (ImageView) findViewById(R.id.img_profile_post);
        gallery = (ImageView) findViewById(R.id.img_profile_gallery);
        info = (ImageView) findViewById(R.id.img_profile_info);

    }

    @Override
    public void onClick(View view) {


    }

}
