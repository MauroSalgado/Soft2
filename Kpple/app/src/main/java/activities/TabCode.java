package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import co.edu.konranlorenz.kpple.R;

public class TabCode extends android.support.v4.app.Fragment implements View.OnClickListener{

    ImageView imgNewCode;
    TextInputEditText txtCode;
    Button btnSaveCode;
    private Boolean codeGenerated = false;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference refUser, refTokens;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_code, container, false);
        refUser = FirebaseDatabase.getInstance().getReference("User");
        refTokens= FirebaseDatabase.getInstance().getReference("Tokens");
        imgNewCode = (ImageView) view.findViewById(R.id.imgNewCode);
        imgNewCode.setOnClickListener(this);
        txtCode = (TextInputEditText) view.findViewById(R.id.txtCode);
        btnSaveCode = (Button) view.findViewById(R.id.btnSaveCode);
        btnSaveCode.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSaveCode:
                if (TextUtils.isEmpty(txtCode.getText())){
                    Toast.makeText(getContext(), "Wrong Code.", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String id = user.getUid();
                    String text = txtCode.getText().toString();

                    if (codeGenerated == true){
                        refUser.child(id).child("code").setValue(text);
                        Toast.makeText(getContext(), "Code Saved.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), ProfileActivity.class));
                    }else{
                        String searchCode = txtCode.getText().toString();
                        if (refTokens.child(searchCode).toString().equals("")){
                            Toast.makeText(getContext(), "Wrong Code.", Toast.LENGTH_SHORT).show();
                        }else{
                            refUser.child(id).child("code").setValue(text);
                            refTokens.child(searchCode).setValue("Yes");
                            Toast.makeText(getContext(), "Code Saved.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(), ProfileActivity.class));
                        }
                    }
                }
                break;
            case R.id.imgNewCode:
                txtCode.setText(generateCode());
                break;
        }
    }

    private String generateCode() {
        String code = refTokens.push().getKey();
        refTokens.child(code).setValue("No");
        txtCode.setText(code);
        Snackbar snackbar = Snackbar.
                make(getView(), "Share this code with your couple", BaseTransientBottomBar.LENGTH_INDEFINITE).
                setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"OK",Toast.LENGTH_SHORT).show();
                    }
                });
        snackbar.show();
        codeGenerated = true;
        return code;
    }
}
