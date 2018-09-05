package activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import co.edu.konranlorenz.kpple.R;

public class PublicationPostActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton mBotonPicture;
    private Button mBotonUploadPost;
    private EditText mEditTextPost;
    private ImageView mImageViewPost;
    private ProgressBar mProgressBarPost;

    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_post);

        mBotonPicture = findViewById(R.id.btn_post_img);
        mBotonUploadPost = findViewById(R.id.buttonPublicPost);
        mEditTextPost = findViewById(R.id.editText_public_Post);
        mImageViewPost = findViewById(R.id.image_public_post);
        mProgressBarPost = findViewById(R.id.progressBar_public_post);

        mBotonPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mBotonUploadPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(mImageViewPost);
            mImageViewPost.setVisibility(View.VISIBLE);
        }
    }
}
