package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shashank.sony.fancytoastlib.FancyToast;

import co.edu.konranlorenz.kpple.R;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity  implements OnClickListener{

    private Button btnRegister;
    private EditText ediTxtMail;
    private EditText ediTxtPass;
    private TextView txtViewSignIn;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        btnRegister = findViewById(R.id.email_sign_in_button);
        ediTxtMail = findViewById(R.id.email);
        ediTxtPass = findViewById(R.id.password);
        txtViewSignIn = findViewById(R.id.txtSingUp);
        progressBar = findViewById(R.id.login_progress);

        btnRegister.setOnClickListener(this);
        txtViewSignIn.setOnClickListener(this);
    }

    private void registerUser(){
        String email = ediTxtMail.getText().toString().trim();
        String pass = ediTxtPass.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            FancyToast.makeText(this, "Please enter mail", FancyToast.LENGTH_SHORT, FancyToast.ERROR,true).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            FancyToast.makeText(this, "Please enter a valid mail", FancyToast.LENGTH_SHORT, FancyToast.ERROR,true).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            FancyToast.makeText(this, "Please enter password", FancyToast.LENGTH_SHORT, FancyToast.ERROR,true).show();
            return;
        }
        if (pass.length() < 6) {
            FancyToast.makeText(this, "Minimum lenght of password should be 6", FancyToast.LENGTH_SHORT, FancyToast.ERROR,true).show();
            return;
        }

        progressBar.setVisibility(ProgressBar.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressBar.setVisibility(ProgressBar.INVISIBLE);
                            FancyToast.makeText(RegisterActivity.this, "Registered Succesfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,true).show();
                            startActivity(new Intent(getBaseContext(), ActivityFormRegister.class));
                        }else{
                            FancyToast.makeText(RegisterActivity.this, "Could not register. Please try again", FancyToast.LENGTH_SHORT, FancyToast.ERROR,true).show();
                            progressBar.setVisibility(ProgressBar.INVISIBLE);
                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == btnRegister){
            registerUser();
        }
        if (v == txtViewSignIn){
            startActivity(new Intent(getBaseContext(), LoginActivity.class));
        }
    }
}

