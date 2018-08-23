package activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.edu.konranlorenz.kpple.R;
import entities.User;

public class TabProfile extends android.support.v4.app.Fragment implements View.OnClickListener{

    TextInputEditText txtInpName;
    Spinner spnSex;
    TextInputEditText txtBirthDate;
    Spinner spnCountry;
    Spinner spnCity;
    Button btnSaveProfile;

    FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_profile, container, false);
        mAuth = FirebaseAuth.getInstance();
        txtInpName = (TextInputEditText) view.findViewById(R.id.txtInpName);
        spnSex = (Spinner) view.findViewById(R.id.spnSex);
        spnCountry = (Spinner) view.findViewById(R.id.spnCountry);
        spnCity = (Spinner) view.findViewById(R.id.spnCity);
        btnSaveProfile = (Button) view.findViewById(R.id.btnSaveProfile);
        btnSaveProfile.setOnClickListener(this);
        txtBirthDate = (TextInputEditText)view.findViewById(R.id.txtBirthDate);
        txtBirthDate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtBirthDate:
                showDatePickerDialog();
                break;
            case R.id.btnSaveProfile:
                saveProfile();
        }
    }

    private void saveProfile() {
        String name= txtInpName.getText().toString();
        if(name.isEmpty()){
            txtInpName.setError("Name Required");
            txtInpName.requestFocus();
            return;
        }

        FirebaseUser user = mAuth.getCurrentUser();
        String id = user.getUid();
        String sex = spnSex.getSelectedItem().toString();
        String country = spnCountry.getSelectedItem().toString();
        String city = spnCity.getSelectedItem().toString();
        String birthdate = txtBirthDate.getText().toString();


        User newUser = new User();
    }

    private void showDatePickerDialog() {
        //DatePickerFragment newFragment = new DatePickerFragment();
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                txtBirthDate.setText(selectedDate);
            }
        });
        newFragment.show(getActivity().getFragmentManager(),"datePicker");
    }
}
