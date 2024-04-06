package com.example.tp2;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SignUpFragment extends Fragment {

    private EditText name, surname, birthDate, phoneNumber, email;
    private CheckBox sport, music, reading;
    private Button submitButton;

    public SignUpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        name = view.findViewById(R.id.editTextName);
        surname = view.findViewById(R.id.editTextSurname);
        birthDate = view.findViewById(R.id.editTextBirthDate);
        phoneNumber = view.findViewById(R.id.editTextPhone);
        email = view.findViewById(R.id.editTextEmail);
        sport = view.findViewById(R.id.checkBoxSport);
        music = view.findViewById(R.id.checkBoxMusic);
        reading = view.findViewById(R.id.checkBoxReading);
        submitButton = view.findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToNextFragment();
            }
        });

        return view;
    }

    private void sendDataToNextFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("name", name.getText().toString());
        bundle.putString("surname", surname.getText().toString());
        bundle.putString("birthDate", birthDate.getText().toString());
        bundle.putString("phoneNumber", phoneNumber.getText().toString());
        bundle.putString("email", email.getText().toString());
        bundle.putBoolean("sport", sport.isChecked());
        bundle.putBoolean("music", music.isChecked());
        bundle.putBoolean("reading", reading.isChecked());

        DisplayFragment displayFragment = new DisplayFragment();
        displayFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, displayFragment)
                .addToBackStack(null)
                .commit();
    }
}
