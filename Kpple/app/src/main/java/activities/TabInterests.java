package activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class TabInterests extends android.support.v4.app.Fragment implements View.OnClickListener{

    StickySwitch stcSwTrio;
    StickySwitch stcSwVouyer;
    StickySwitch stcSwSadism;
    StickySwitch stcSwFetichism;
    StickySwitch stcSwMasoquism;
    StickySwitch stcSwSwinger;
    Button btnSaveInterest;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference refUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_interests, container, false);
        refUser = FirebaseDatabase.getInstance().getReference("Interest");
        stcSwTrio = (StickySwitch) view.findViewById(R.id.stcSwTrio);
        //stcSwTrio.setOnClickListener(this);
        stcSwVouyer = (StickySwitch) view.findViewById(R.id.stcSwVouyer);
        //stcSwVouyer.setOnClickListener(this);
        stcSwSadism = (StickySwitch) view.findViewById(R.id.stcSwSadism);
        //stcSwSadism.setOnClickListener(this);
        stcSwFetichism = (StickySwitch) view.findViewById(R.id.stcSwFetichism);
        //stcSwFetichism.setOnClickListener(this);
        stcSwMasoquism = (StickySwitch) view.findViewById(R.id.stcSwMasoquism);
        //stcSwMasoquism.setOnClickListener(this);
        stcSwSwinger = (StickySwitch) view.findViewById(R.id.stcSwSwinger);
        //stcSwSwinger.setOnClickListener(this);

        btnSaveInterest = (Button) view.findViewById(R.id.btnSaveInterest);
        btnSaveInterest.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnSaveInterest:
                FirebaseUser user = mAuth.getCurrentUser();

                refUser.child("Trio").child(user.getUid()).setValue(stcSwTrio.getText());
                refUser.child("Vouyerism").child(user.getUid()).setValue(stcSwVouyer.getText());
                refUser.child("Sadism").child(user.getUid()).setValue(stcSwSadism.getText());
                refUser.child("Fetichism").child(user.getUid()).setValue(stcSwFetichism.getText());
                refUser.child("Masoquism").child(user.getUid()).setValue(stcSwMasoquism.getText());
                refUser.child("Swinger").child(user.getUid()).setValue(stcSwSwinger.getText());

                Toast.makeText(getContext(), "Interest Saved. Please continue to Hobbies", Toast.LENGTH_SHORT).show();
                btnSaveInterest.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
