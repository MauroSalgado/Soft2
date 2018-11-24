package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import entities.Block;
import entities.User;
import lib.FirebaseFunctions;

public class Recycler_cardview_person extends Fragment {

    private RecyclerView mRecyclerView;
    private PersonCardAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private List<User> mUser;
    private List<Block> mBlockUsers;
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
        mBlockUsers = new ArrayList<>();
        Intent intent = getActivity().getIntent();
        String userId = intent.getStringExtra(MESSAGE_KEY);
        if (userId == null) {
            FirebaseUser user = mAuth.getCurrentUser();
            userId = user.getUid();
        }

        // Consultar Bloqueados

        FirebaseFunctions fbFunctions = new FirebaseFunctions();
        String Uid = fbFunctions.getIdUsuarioFire();
        DatabaseReference refBlockUser = fbFunctions.getReferenceBlockById(Uid);
        refBlockUser.addValueEventListener(new ValueEventListener() {
            ArrayList<String> userBlock= new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    Block block1 = userSnapshot.getValue(Block.class);
                    String idUBloc = block1.getIdUserBlock();
                    userBlock.add(idUBloc);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        //  ------------------------------------------------

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("User");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseFunctions fbFunctions = new FirebaseFunctions();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User user = postSnapshot.getValue(User.class);
                    if(!user.getIdUser().equals(fbFunctions.getIdUsuarioFire())){
                        mUser.add(user);
                    }


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
