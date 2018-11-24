package activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import co.edu.konranlorenz.kpple.R;
import co.edu.konranlorenz.kpple.TabPrincipalController;

/**
 * A login screen that offers login via email/password.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "EmailPassword";

    private EditText mEmailField;
    private EditText mPasswordField;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Redirección a la vista de nuevo usuario

        TextView btn = findViewById(R.id.register_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intento);
            }
        });

        // Views
        mEmailField = findViewById(R.id.field_email);
        mPasswordField = findViewById(R.id.field_password);

        // Buttons
        findViewById(R.id.singin_button).setOnClickListener(this);
        findViewById(R.id.forgot_password).setOnClickListener(this);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
    }


    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null && currentUser.getDisplayName() != null) {
            finish();
            //startActivity(new Intent(this, ProfileActivity.class));
            startActivity(new Intent(this, TabPrincipalController.class));
        }


    }
    // [END on_start_check_user]


    /*---------------------Login-----------------------*/
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }


        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent intentp = new Intent(LoginActivity.this, TabPrincipalController.class);
                            startActivity(intentp);
                            overridePendingTransition(R.anim.zoom_back_in,R.anim.zoom_back_out);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            FancyToast.makeText(LoginActivity.this, "Authentication failed", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            FancyToast.makeText(LoginActivity.this, "Authentication failed", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    /*-------------------Login-----------------------*/
    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    /*-------------------Login-----------------------*/
    private boolean validateTxtEmail() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }
        return valid;
    }

    /*-------------------Reset Password-----------------------*/

    private void recoverPass(String email) {
        if (!validateTxtEmail()) {
            return;
        }

        mAuth = FirebaseAuth.getInstance();
        String emailAddress = mEmailField.getText().toString();

        mAuth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent");
                            FancyToast.makeText(LoginActivity.this, "Mail sent with instructions", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                        } else {
                            FancyToast.makeText(LoginActivity.this, "Email no Register in the APP", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        }
                    }
                });
    }
    /*-------------------Login-----------------------*/

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.singin_button) {
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
        }
        if (i == R.id.forgot_password) {
            recoverPass(mEmailField.getText().toString());
        }
    }

    private void updateUI(FirebaseUser user) {
        /*
        if (user != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
        } else {
           mStatusTextView.setText(R.string.signed_out);
        }
        */

    }

    private void signOut() {
        mAuth.signOut();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item_menu) {
        int id = item_menu.getItemId();
        if (id == R.id.logout_menu_main) {
            signOut();
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

}

