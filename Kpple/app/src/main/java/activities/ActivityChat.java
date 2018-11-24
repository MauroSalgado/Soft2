package activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import adapters.MessageAdapter;
import co.edu.konranlorenz.kpple.R;
import de.hdodenhof.circleimageview.CircleImageView;
import entities.MessageGet;
import entities.MessageSend;
import entities.User;

public class ActivityChat extends AppCompatActivity {

    private CircleImageView photoProfile;
    private TextView name;
    private RecyclerView rvMesg;
    private EditText txtMessage;
    private ImageButton btnSend;
    private MessageAdapter adapter;
    private ImageButton btnSendPhoto;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseAuth mAuth;

    private static final int PHOTO_SEND = 1;
    private String USER_NAME;
    private String urlPhoto;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SEND && resultCode == RESULT_OK) {
            Uri u = data.getData();
            storageReference = storage.getReference("chat_img");
            final StorageReference photRef = storageReference.child(u.getLastPathSegment());
            photRef.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri u = taskSnapshot.getDownloadUrl();
                    MessageSend m = new MessageSend("Post a new photo", u.toString(), USER_NAME, urlPhoto, "2", ServerValue.TIMESTAMP);
                    databaseReference.push().setValue(m);
                }
            });
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        //photoProfile = findViewById(R.id.imgChat);
        //name = findViewById(R.id.txtName);
        rvMesg = findViewById(R.id.recViewChat);
        txtMessage = findViewById(R.id.txtMessage);
        btnSend = findViewById(R.id.btnSendChat);
        btnSendPhoto = findViewById(R.id.btnImgChat);
        urlPhoto = "";

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Chat");//Sala de chat
        storage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance();

        adapter = new MessageAdapter(this);
        rvMesg.setHasFixedSize(true);
        rvMesg.setLayoutManager(new LinearLayoutManager(this));
        rvMesg.setAdapter(adapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new MessageSend(txtMessage.getText().toString(), USER_NAME, urlPhoto, "1", ServerValue.TIMESTAMP));
                txtMessage.setText("");
            }
        });

        btnSendPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Upload photo"), PHOTO_SEND);
            }
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MessageGet m = dataSnapshot.getValue(MessageGet.class);
                adapter.addMensaje(m);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            btnSend.setEnabled(false);
            DatabaseReference reference = database.getReference("User/"+currentUser.getUid());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    USER_NAME = user.getName();
                    //name.setText(USER_NAME);
                    btnSend.setEnabled(true);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{
            returnLogin();
        }
    }

    private void returnLogin(){
        startActivity(new Intent(ActivityChat.this, LoginActivity.class));
        finish();
    }
    private void setScrollbar() {
        rvMesg.scrollToPosition(adapter.getItemCount() - 1);
    }
}
