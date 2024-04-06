package com.example.tp2;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
            }
        });

        return view;
    }
}
