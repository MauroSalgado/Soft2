package activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import adapters.PostAdapter;
import co.edu.konranlorenz.kpple.R;
import entities.Post;

public class PostViewer extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef, refUser;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    private List<Post> mPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_viewer);

        mRecyclerView = findViewById(R.id.recViewPost);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPosts = new ArrayList<>();
        refUser = FirebaseDatabase.getInstance().getReference("User");
        FirebaseUser user = mAuth.getCurrentUser();

        String url = "Post/"+user.getUid();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Post/4Fy1OMGn9lOSkSkZctpjAgTp1Mp2/0WpUMWwcsDW7iPVuoHUnxWKI7RH2");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    //Post post = postSnapshot.getValue(Post.class);
                    //mPosts.add(post);

                    String nombre = dataSnapshot.child("txtPost").getValue().toString();
                    Log.i("TAG_DEBUG",nombre);
                }
                //mAdapter = new PostAdapter(PostViewer.this, mPosts);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(PostViewer.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
