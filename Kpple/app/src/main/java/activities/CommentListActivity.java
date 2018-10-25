package activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import adapters.CommentCardAdapter;
import co.edu.konranlorenz.kpple.R;
import de.hdodenhof.circleimageview.CircleImageView;
import entities.Comment;

public class CommentListActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private EditText mTxtcomment;
    private ImageView mBtnSend;
    private CircleImageView mImageProfile;
    private TextView mName;

    private CommentCardAdapter adapter;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReferenceM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        mRecyclerView = findViewById(R.id.recyclerView_comments);
        mTxtcomment = findViewById(R.id.edit_txt_comment);
        mBtnSend = findViewById(R.id.btn_send_comment);
        mImageProfile = findViewById(R.id.foto_perfil_comment);
        mName = findViewById(R.id.txtv_name_input);

        adapter = new CommentCardAdapter(this);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReferenceM = mDatabase.getReference("Comment");

        LinearLayoutManager linear = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linear);
        mRecyclerView.setAdapter(adapter);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //adapter.addComment(new Comment(mTxtcomment.getText().toString(),mName.getText().toString(),"","1","00:00"));
                mDatabaseReferenceM.push().setValue(new Comment(mTxtcomment.getText().toString(),mName.getText().toString(),"","1","00:00"));
                mTxtcomment.setText("");
            }
        });

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
                Comment comment = dataSnapshot.getValue(Comment.class);
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
}
