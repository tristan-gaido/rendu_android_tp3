package com.example.tp2;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SignUpFragment extends Fragment {

    private EditText name, surname, birthDate, phoneNumber, email;
    private CheckBox sport, music, reading;
    private Button submitButton, downloadButton, parseButton;

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
        downloadButton = view.findViewById(R.id.buttonDownload);
        parseButton = view.findViewById(R.id.buttonParse);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToNextFragment();
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataFromFile();
            }
        });

        parseButton.setOnClickListener(v -> startParsingService());


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

    private void loadDataFromFile() {
        try {
            FileInputStream fis = getActivity().openFileInput("UserData.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String[] details = sb.toString().split("\n");
            name.setText(details[0].split(": ")[1]);
            surname.setText(details[1].split(": ")[1]);
            birthDate.setText(details[2].split(": ")[1]);
            phoneNumber.setText(details[3].split(": ")[1]);
            email.setText(details[4].split(": ")[1]);
            sport.setChecked(details[5].contains("Sport"));
            music.setChecked(details[5].contains("Musique"));
            reading.setChecked(details[5].contains("Lecture"));

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Erreur lors du chargement", Toast.LENGTH_SHORT).show();
        }
    }

    private void startParsingService() {
        DownloadAndParseService.startActionParseData(getContext());
        loadDataFromFile();
    }

}
