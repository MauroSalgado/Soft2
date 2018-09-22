package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

import adapters.PostAdapter;
import co.edu.konranlorenz.kpple.R;
import entities.Post;

public class PostViewerFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private List<Post> mPosts;
    private final static String MESSAGE_KEY = "null";

    public PostViewerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_post_viewer, container, false);
        mRecyclerView = v.findViewById(R.id.recViewPost);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mPosts = new ArrayList<>();
        Intent intent = getActivity().getIntent();
        String userId = intent.getStringExtra(MESSAGE_KEY);
        //String userId = "27M4a845YeRSWejMijro3FWGIRj2";
        if (userId == null) {
            FirebaseUser user = mAuth.getCurrentUser();
            userId = user.getUid();
        }

        String url = "Post/" + userId;

        FloatingActionButton fab = v.findViewById(R.id.fab_button_float);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PublicationPostActivity.class);
                startActivity(intent);
            }
        });

        mDatabaseRef = FirebaseDatabase.getInstance().getReference(url);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Post post = postSnapshot.getValue(Post.class);
                    mPosts.add(post);
                }
                mAdapter = new PostAdapter(getActivity(), mPosts);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                FancyToast.makeText(getActivity(), databaseError.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
            }
        });
        return v;
    }
}
