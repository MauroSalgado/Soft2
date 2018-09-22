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

import adapters.PersonCardAdapter;
import co.edu.konranlorenz.kpple.R;
import entities.User;

public class Recycler_cardview_person extends Fragment {

    private RecyclerView mRecyclerView;
    private PersonCardAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private List<User> mUser;
    private final static String MESSAGE_KEY = "null";

    public Recycler_cardview_person() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_recycler_cardview_person, container, false);
        mRecyclerView = v.findViewById(R.id.recyclerview_card_person);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mUser = new ArrayList<>();
        Intent intent = getActivity().getIntent();
        String userId = intent.getStringExtra(MESSAGE_KEY);
        if (userId == null) {
            FirebaseUser user = mAuth.getCurrentUser();
            userId = user.getUid();
        }



        mDatabaseRef = FirebaseDatabase.getInstance().getReference("User");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User user = postSnapshot.getValue(User.class);
                    mUser.add(user);
                }
                mAdapter = new PersonCardAdapter(getActivity(), mUser, getContext());
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
