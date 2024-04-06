package com.example.tp2;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileOutputStream;

public class DisplayFragment extends Fragment {

    private TextView infoTextView;
    private Button validateButton;

    public DisplayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        infoTextView = view.findViewById(R.id.textViewInfo);
        validateButton = view.findViewById(R.id.buttonValidate);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String info = "Nom: " + bundle.getString("name") + "\n" +
                    "Prénom: " + bundle.getString("surname") + "\n" +
                    "Date de naissance: " + bundle.getString("birthDate") + "\n" +
                    "Numéro de téléphone: " + bundle.getString("phoneNumber") + "\n" +
                    "Email: " + bundle.getString("email") + "\n" +
                    "Centre d'intérêts: " +
                    (bundle.getBoolean("sport") ? "Sport, " : "") +
                    (bundle.getBoolean("music") ? "Musique, " : "") +
                    (bundle.getBoolean("reading") ? "Lecture" : "");

            infoTextView.setText(info);
        }

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToFile();
            }
        });

        return view;
    }

    private void saveDataToFile() {
        String fileName = "UserData.txt";
        StringBuilder data = new StringBuilder();
        Bundle bundle = getArguments();
        if (bundle != null) {
            data.append("Nom: ").append(bundle.getString("name")).append("\n")
                    .append("Prénom: ").append(bundle.getString("surname")).append("\n")
                    .append("Date de naissance: ").append(bundle.getString("birthDate")).append("\n")
                    .append("Numéro de téléphone: ").append(bundle.getString("phoneNumber")).append("\n")
                    .append("Email: ").append(bundle.getString("email")).append("\n")
                    .append("Centre d'intérêts: ")
                    .append(bundle.getBoolean("sport") ? "Sport, " : "")
                    .append(bundle.getBoolean("music") ? "Musique, " : "")
                    .append(bundle.getBoolean("reading") ? "Lecture" : "").append("\n");
        }

        try {
            FileOutputStream fos = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(data.toString().getBytes());
            fos.close();
            Toast.makeText(getActivity(), "Données enregistrées", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show();
        }
    }
}
