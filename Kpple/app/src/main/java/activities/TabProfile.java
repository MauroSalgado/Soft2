package activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.edu.konranlorenz.kpple.R;
import entities.User;

public class TabProfile extends android.support.v4.app.Fragment implements View.OnClickListener {

    TextInputEditText txtInpName;
    Spinner spnSex;
    TextInputEditText txtBirthDate;
    Spinner spnCountry;
    Spinner spnCity;
    Spinner spnLanguage;
    Button btnSaveProfile;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference refUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_profile, container, false);
        refUser = FirebaseDatabase.getInstance().getReference("User");
        txtInpName = (TextInputEditText) view.findViewById(R.id.txtInpName);
        spnSex = (Spinner) view.findViewById(R.id.spnSex);
        spnCountry = (Spinner) view.findViewById(R.id.spnCountry);
        spnCity = (Spinner) view.findViewById(R.id.spnCity);
        spnLanguage = (Spinner) view.findViewById(R.id.spnLanguage);
        btnSaveProfile = (Button) view.findViewById(R.id.btnSaveProfile);
        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });
        txtBirthDate = (TextInputEditText) view.findViewById(R.id.txtBirthDate);
        txtBirthDate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtBirthDate:
                showDatePickerDialog();
                break;
        }
    }

    private void saveProfile() {
        String name = txtInpName.getText().toString();
        if (name.isEmpty()) {
            txtInpName.setError("Name Required");
            txtInpName.requestFocus();
            return;
        }

        FirebaseUser user = mAuth.getCurrentUser();
        String id = user.getUid();
        String city = spnCity.getSelectedItem().toString();
        String country = spnCountry.getSelectedItem().toString();
        String sex = spnSex.getSelectedItem().toString();
        String date = txtBirthDate.getText().toString();
        String nick = user.getDisplayName().toString();
        String language = spnLanguage.getSelectedItem().toString();

        User newUser = new User(id, city, country, sex, date, nick, name, language, "",false);

        refUser.child(id).setValue(newUser);
        btnSaveProfile.setVisibility(View.INVISIBLE);
        Toast.makeText(getContext(), "Profile Saved. Please continue to Interest", Toast.LENGTH_SHORT).show();
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month + 1) + " / " + year;
                txtBirthDate.setText(selectedDate);
            }
        });
        newFragment.show(getActivity().getFragmentManager(), "datePicker");
    }
}
