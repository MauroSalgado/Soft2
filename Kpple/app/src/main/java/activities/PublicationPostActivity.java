package activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import co.edu.konranlorenz.kpple.R;
import entities.Post;

public class PublicationPostActivity extends AppCompatActivity implements DialogUrlgFragment.OnFragmentInteractionListener {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageButton mBotonPicture;
    private Button mBotonUploadPost;
    private EditText mEditTextPost;
    private ImageView mImageViewPost;
    private ImageButton mBotonVideo;
    private DatabaseReference mDatabaseRef;
    private ProgressBar mProgressBarPost;

    private Uri mImageUri;

    private String urlImage="";
    private String textPost="";
    private String urlVideo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_post);

        mBotonPicture = findViewById(R.id.btn_post_img);
        mBotonUploadPost = findViewById(R.id.buttonPublicPost);
        mEditTextPost = findViewById(R.id.editText_public_Post);
        mImageViewPost = findViewById(R.id.image_public_post);
        mProgressBarPost = findViewById(R.id.progressBar_public_post);
        mBotonVideo = findViewById(R.id.btn_post_video);

        mBotonPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mBotonUploadPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarPost();
            }
        });

        mBotonVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUrlgFragment newFragment = new DialogUrlgFragment();
                newFragment.show(getSupportFragmentManager(), "missiles");
            }
        });
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    private void guardarPost(){
        this.textPost = mEditTextPost.getText().toString();
        Calendar cfecha = Calendar.getInstance();
        String strFechacfecha=cfecha.toString();
        final String idU = GetId();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Post/"+idU);
        String code = mDatabaseRef.push().getKey();
        Post post = new Post(code,idU,strFechacfecha,this.urlImage,this.urlVideo, this.textPost);
        mDatabaseRef.child(code).setValue(post);
        FancyToast.makeText(getBaseContext(), "Publicación Subida con éxito.", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(mImageViewPost);
            mImageViewPost.setVisibility(View.VISIBLE);

            uploadImageFBStorage();

        }
    }

    public String GetId(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String idU = user.getUid();
        return idU;
    }



    private void uploadImageFBStorage() {
        String idU = GetId();
        final StorageReference profImgref = FirebaseStorage.getInstance().getReference("/" + idU  + "/post");
        if (mImageUri != null) {
            mProgressBarPost.setVisibility(View.VISIBLE);
            profImgref
                    .putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            mProgressBarPost.setVisibility(View.GONE);
                            urlImage = taskSnapshot.getDownloadUrl().toString();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mProgressBarPost.setVisibility(View.GONE);
                            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
