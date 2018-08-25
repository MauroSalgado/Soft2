package activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.edu.konranlorenz.kpple.R;
import io.ghyeok.stickyswitch.widget.StickySwitch;

public class TabHobbies extends android.support.v4.app.Fragment implements View.OnClickListener{

    StickySwitch stcSwCoffee;
    StickySwitch stcSwMovies;
    StickySwitch stcSwParty;
    StickySwitch stcSwPark;
    StickySwitch stcSwVideo;
    StickySwitch stcSwSwinger;
    Button btnSaveHobbies;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference refUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_hobbies, container, false);
        refUser = FirebaseDatabase.getInstance().getReference("Hobbies");
        stcSwCoffee = (StickySwitch) view.findViewById(R.id.stcSwCoffee);
        stcSwMovies = (StickySwitch) view.findViewById(R.id.stcSwMovies);
        stcSwParty = (StickySwitch) view.findViewById(R.id.stcSwParty);
        stcSwPark = (StickySwitch) view.findViewById(R.id.stcSwPark);
        stcSwVideo = (StickySwitch) view.findViewById(R.id.stcSwVideo);
        stcSwSwinger = (StickySwitch) view.findViewById(R.id.stcSwSwinger);
        btnSaveHobbies = (Button) view.findViewById(R.id.btnSaveHobbies);
        btnSaveHobbies.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSaveHobbies:
                FirebaseUser user = mAuth.getCurrentUser();

                refUser.child("Coffee").child(user.getUid()).setValue(stcSwCoffee.getText());
                refUser.child("Movies").child(user.getUid()).setValue(stcSwMovies.getText());
                refUser.child("Party").child(user.getUid()).setValue(stcSwParty.getText());
                refUser.child("Park").child(user.getUid()).setValue(stcSwPark.getText());
                refUser.child("VideoCall").child(user.getUid()).setValue(stcSwVideo.getText());
                refUser.child("Swinger").child(user.getUid()).setValue(stcSwSwinger.getText());

                Toast.makeText(getContext(), "Hobbies Saved. Please continue to Code", Toast.LENGTH_SHORT).show();
                btnSaveHobbies.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
