package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;

import co.edu.konranlorenz.kpple.R;
import co.edu.konranlorenz.kpple.TabPrincipalController;

public class TabCode extends android.support.v4.app.Fragment implements View.OnClickListener {

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
        refTokens = FirebaseDatabase.getInstance().getReference("Tokens");
        imgNewCode = view.findViewById(R.id.imgNewCode);
        imgNewCode.setOnClickListener(this);
        txtCode = view.findViewById(R.id.txtCode);
        btnSaveCode = view.findViewById(R.id.btnSaveCode);
        btnSaveCode.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSaveCode:
                if (TextUtils.isEmpty(txtCode.getText())) {
                    FancyToast.makeText(getContext(), "Wrong Code", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                    return;
                } else {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String id = user.getUid();
                    String text = txtCode.getText().toString();

                    if (codeGenerated == true) {
                        refUser.child(id).child("code").setValue(text);
                        FancyToast.makeText(getContext(), "Code Saved", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                        startActivity(new Intent(getContext(), TabPrincipalController.class));
                    } else {
                        String searchCode = txtCode.getText().toString();
                        if (refTokens.child(searchCode).toString().equals("")) {
                            FancyToast.makeText(getContext(), "Wrong Code", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        } else {
                            refUser.child(id).child("code").setValue(text);
                            refTokens.child(searchCode).setValue("Yes");
                            FancyToast.makeText(getContext(), "Code Saved", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                            startActivity(new Intent(getContext(), TabPrincipalController.class));
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
        final Snackbar snackbar = Snackbar.make(getView(), "Share this code with your couple", Snackbar.LENGTH_INDEFINITE);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(getView().getContext(), R.color.colorPrimary));
        snackbar.setActionTextColor(getResources().getColor(R.color.white));
        snackbar.show();
        snackbar.setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        codeGenerated = true;
        return code;
    }
}
