package com.example.alfian04;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MapsFragment extends Fragment {

    private EditText etMapsLocation;

    public MapsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        etMapsLocation = view.findViewById(R.id.et_maps_location);
        Button btnSearchLocation = view.findViewById(R.id.btn_search_location);
        Button btnShortcutMonas = view.findViewById(R.id.btn_shortcut_monas);

        btnSearchLocation.setOnClickListener(v -> searchLocation());

        btnShortcutMonas.setOnClickListener(v -> {
            etMapsLocation.setText("Monas Jakarta");
            searchLocation();
        });

        return view;
    }

    private void searchLocation() {
        String query = etMapsLocation.getText().toString().trim();
        if (TextUtils.isEmpty(query)) {
            etMapsLocation.setError(getString(R.string.err_empty_location));
            etMapsLocation.requestFocus();
            return;
        }

        try {
            Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(query));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            startActivity(mapIntent);
        } catch (Exception e) {
            if (getContext() != null) {
                Toast.makeText(getContext(), "Tidak dapat membuka aplikasi Google Maps", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
