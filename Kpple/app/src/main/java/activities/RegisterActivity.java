package activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
        btnRegister = (Button) findViewById(R.id.email_sign_in_button);
        ediTxtMail = (EditText) findViewById(R.id.email);
        ediTxtPass = (EditText) findViewById(R.id.password);
        txtViewSignIn = (TextView) findViewById(R.id.txtSingUp);
        progressBar = (ProgressBar) findViewById(R.id.login_progress);

        btnRegister.setOnClickListener(this);
        txtViewSignIn.setOnClickListener(this);
    }

    private void registerUser(){
        String email = ediTxtMail.getText().toString().trim();
        String pass = ediTxtPass.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(ProgressBar.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressBar.setVisibility(ProgressBar.INVISIBLE);
                            Toast.makeText(RegisterActivity.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Could not register. Please try again", Toast.LENGTH_SHORT).show();
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
            //Open Login Activity
        }
    }
}

