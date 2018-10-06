package activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.shashank.sony.fancytoastlib.FancyToast;

import co.edu.konranlorenz.kpple.R;
import lib.FirebaseFunctions;

public class CalificationCommunityActivity extends AppCompatActivity {

    RatingBar mRatingbar;
    TextView mTxtprueba;
    Button mButtonenviar;
    String uiduser;
    float mRatingP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseFunctions firebaseFunctions= new FirebaseFunctions();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calification_community);

        mTxtprueba = (TextView)findViewById(R.id.textViewprueba);
        mRatingbar = (RatingBar)findViewById(R.id.ratingBarComunity);
        mButtonenviar = (Button)findViewById(R.id.button_enviar_rating);

        Intent intent = getIntent();
        Bundle bdiduser = intent.getExtras();
        if(bdiduser != null)
        {
            uiduser = (String) bdiduser.get("idcommunity");
        }

        mRatingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mTxtprueba.setText("Rating: "+v);
                mRatingP=v;
            }
        });

        Log.i("LOGCALI",uiduser);
        mButtonenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference comunityRef=firebaseFunctions.getReferenceCommunityByID(uiduser);
                comunityRef.child("calification").setValue(mRatingP);
                FancyToast.makeText(getApplicationContext(), "Calificación enviada", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
            }
        });


    }
}
