package activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import co.edu.konranlorenz.kpple.R;

public class TabProfile extends android.support.v4.app.Fragment implements View.OnClickListener{
    TextInputEditText txtDate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_profile, container, false);
        TextInputEditText txtDate = (TextInputEditText)view.findViewById(R.id.txtBirthDate);
        txtDate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtBirthDate:
                showDatePickerDialog(txtDate);
                break;
        }
    }

    private void showDatePickerDialog(final EditText txtDate) {
        //DatePickerFragment newFragment = new DatePickerFragment();
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                txtDate.setText(selectedDate);
            }
        });
        newFragment.show(getActivity().getFragmentManager(),"datePicker");
    }
}
