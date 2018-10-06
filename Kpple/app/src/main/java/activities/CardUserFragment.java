package activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import com.twitter.sdk.android.core.models.Card;

import java.util.ArrayList;
import java.util.List;

import adapters.FindNameAdapter;
import adapters.PostAdapter;
import co.edu.konranlorenz.kpple.R;
import entities.Post;
import entities.User;


public class CardUserFragment extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FindNameAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private List<User> mUser;
    private final static String MESSAGE_KEY = "null";

    public CardUserFragment() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_viewer);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUser = new ArrayList<>();
        Intent intent = getIntent();
        String userId = intent.getStringExtra(MESSAGE_KEY);
        //String userId = "27M4a845YeRSWejMijro3FWGIRj2";
        if (userId.equals("null")) {
            FirebaseUser user = mAuth.getCurrentUser();
            userId = user.getUid();
        }

    }
}
