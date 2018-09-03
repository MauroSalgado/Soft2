package activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.edu.konranlorenz.kpple.R;

public class ProfileActivity extends AppCompatActivity implements PostFragment.OnFragmentInteractionListener, GalleryFragment.OnFragmentInteractionListener, InfoFragment.OnFragmentInteractionListener, PhotoProfile.OnFragmentInteractionListener {

    PostFragment postFragment;
    GalleryFragment galleryFragment;
    InfoFragment infoFragment;
    PhotoProfile photoProfileFragment;
    ImageView post, gallery, imgProfile;
    TextView txtDisplayName;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();

        postFragment = new PostFragment();
        galleryFragment = new GalleryFragment();
        infoFragment = new InfoFragment();
        photoProfileFragment = new PhotoProfile();

        getSupportFragmentManager().beginTransaction().add(R.id.profile_fragment_container, postFragment).commit();

        post = (ImageView) findViewById(R.id.img_profile_post);
        gallery = (ImageView) findViewById(R.id.img_profile_gallery);
        imgProfile = (ImageView) findViewById(R.id.imgProfile);
        txtDisplayName = (TextView) findViewById(R.id.txtDisplayName);

        loadUserInformation();
        invalidateOptionsMenu();
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

    private void signOut() {
        mAuth.signOut();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item_menu) {
        int id = item_menu.getItemId();
        if (id == R.id.logout_menu_main) {
            signOut();
            Intent intent = new Intent(getBaseContext(),LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem cerrarsesion = (MenuItem) menu.findItem(R.id.logout_menu_main);
        if(mAuth.getCurrentUser() != null){
            cerrarsesion.setVisible(true);
        }else{
            cerrarsesion.setVisible(false);
        }

        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onClick(View view) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.img_profile_gallery:
                transaction.replace(R.id.profile_fragment_container,galleryFragment);
                break;
            case R.id.img_profile_info:
                transaction.replace(R.id.profile_fragment_container,infoFragment);
                break;
            case R.id.img_profile_post:
                Intent intent = new Intent(ProfileActivity.this, PostViewer.class);
                startActivity(intent);
                //transaction.replace(R.id.profile_fragment_container,postFragment);
                break;
        }
        transaction.commit();
    }
}
