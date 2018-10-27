package activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import adapters.CommentCardAdapter;
import co.edu.konranlorenz.kpple.R;
import de.hdodenhof.circleimageview.CircleImageView;
import entities.Comment;
import lib.FirebaseFunctions;

public class CommentListActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private EditText mTxtcomment;
    private ImageView mBtnSend;
    private CircleImageView mImageProfile;
    private TextView mName;

    private CommentCardAdapter adapter;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReferenceM;

    private FirebaseStorage mFirebaseStorage;
    private StorageReference mStorageReference;

    private ImageButton mBtnEnviarFoto;
    private static final int PHOTO_SEND = 1;
    private static final int PHOTO_PERFIL = 2;
    private String mFotoPerfilCadena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        mRecyclerView = findViewById(R.id.recyclerView_comments);
        mTxtcomment = findViewById(R.id.edit_txt_comment);
        mBtnSend = findViewById(R.id.btn_send_comment);
        mImageProfile = findViewById(R.id.foto_perfil_comment);
        mName = findViewById(R.id.txtv_name_input);
        mBtnEnviarFoto = (ImageButton) findViewById(R.id.btn_enviar_foto);

        adapter = new CommentCardAdapter(this);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReferenceM = mDatabase.getReference("Comment");

        mFirebaseStorage = FirebaseStorage.getInstance();
        mFotoPerfilCadena = "";

        LinearLayoutManager linear = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linear);
        mRecyclerView.setAdapter(adapter);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFunctions fbfunctions = new FirebaseFunctions();
                //adapter.addComment(new Comment(mTxtcomment.getText().toString(),mName.getText().toString(),"","1","00:00"));
                mDatabaseReferenceM.push().setValue(new CommentEnviar(mTxtcomment.getText().toString(),fbfunctions.getNameUser(),mFotoPerfilCadena,"1",ServerValue.TIMESTAMP));
                mTxtcomment.setText("");
            }
        });

        mBtnEnviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent,"Selecciona una imagen"),PHOTO_SEND);
            }
        });

        /*mImageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent,"Selecciona una imagen"),PHOTO_PERFIL);
            }
        }); */

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollBar();

            }
        });

        mDatabaseReferenceM.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CommentRecibir comment = dataSnapshot.getValue(CommentRecibir.class);
                adapter.addComment(comment);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setScrollBar(){
        this.mRecyclerView.scrollToPosition(adapter.getItemCount()-1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_SEND && resultCode == RESULT_OK){
            Uri uri = data.getData();
            mStorageReference = mFirebaseStorage.getReference("img_comments");
            final StorageReference photo_reference = mStorageReference.child(uri.getLastPathSegment());
            photo_reference.putFile(uri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri uri = taskSnapshot.getDownloadUrl();
                    CommentEnviar comment = new CommentEnviar("Han Enviado una foto",mName.getText().toString(),uri.toString(),mFotoPerfilCadena,"2",ServerValue.TIMESTAMP);
                    mDatabaseReferenceM.push().setValue(comment);
                }
            });
        }else if(requestCode == PHOTO_PERFIL && resultCode == RESULT_OK){
            Uri uri = data.getData();
            mStorageReference = mFirebaseStorage.getReference("foto_perfiles");
            final StorageReference photo_reference = mStorageReference.child(uri.getLastPathSegment());
            photo_reference.putFile(uri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri uri = taskSnapshot.getDownloadUrl();
                    mFotoPerfilCadena = uri.toString();
                    CommentEnviar comment = new CommentEnviar("Han actualizado la foto de perfil",mName.getText().toString(),uri.toString(),mFotoPerfilCadena,"2",ServerValue.TIMESTAMP);
                    mDatabaseReferenceM.push().setValue(comment);
                    Glide.with(CommentListActivity.this).load(uri.toString()).into(mImageProfile);
                }
            });
        }
    }
}
