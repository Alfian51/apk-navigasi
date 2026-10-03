package com.example.alfian04;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FeatureFragment extends Fragment {

    private EditText etStudentName;
    private Spinner spJurusan;
    private EditText etStudentScore;

    public FeatureFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feature, container, false);

        etStudentName = view.findViewById(R.id.et_student_name);
        spJurusan = view.findViewById(R.id.sp_jurusan);
        etStudentScore = view.findViewById(R.id.et_student_score);

        Button btnProcess = view.findViewById(R.id.btn_process_data);
        Button btnReset = view.findViewById(R.id.btn_reset_form);

        // Setup Spinner Jurusan
        String[] listJurusan = new String[]{
                "Rekayasa Perangkat Lunak (RPL)",
                "Teknik Komputer dan Jaringan (TKJ)",
                "Multimedia / Desain Komunikasi Visual (DKV)",
                "Sistem Informasi Jaringan dan Aplikasi (SIJA)",
                "Akuntansi dan Keuangan Lembaga"
        };
        if (getContext() != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    getContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    listJurusan
            );
            spJurusan.setAdapter(adapter);
        }

        btnProcess.setOnClickListener(v -> processForm());
        btnReset.setOnClickListener(v -> resetForm());

        return view;
    }

    private void processForm() {
        String name = etStudentName.getText().toString().trim();
        String scoreStr = etStudentScore.getText().toString().trim();
        String jurusan = spJurusan.getSelectedItem() != null ? spJurusan.getSelectedItem().toString() : "-";

        // Validasi input nama
        if (TextUtils.isEmpty(name)) {
            etStudentName.setError(getString(R.string.err_empty_name));
            etStudentName.requestFocus();
            return;
        }

        // Validasi input nilai
        if (TextUtils.isEmpty(scoreStr)) {
            etStudentScore.setError(getString(R.string.err_empty_score));
            etStudentScore.requestFocus();
            return;
        }

        double score;
        try {
            score = Double.parseDouble(scoreStr);
        } catch (NumberFormatException e) {
            etStudentScore.setError(getString(R.string.err_invalid_score));
            etStudentScore.requestFocus();
            return;
        }

        if (score < 0 || score > 100) {
            etStudentScore.setError(getString(R.string.err_invalid_score));
            etStudentScore.requestFocus();
            return;
        }

        // Hitung Grade dan Status
        String grade;
        String status;
        if (score >= 85) {
            grade = "A (Sangat Baik)";
            status = "LULUS";
        } else if (score >= 75) {
            grade = "B (Baik)";
            status = "LULUS";
        } else if (score >= 60) {
            grade = "C (Cukup)";
            status = "LULUS";
        } else if (score >= 50) {
            grade = "D (Kurang)";
            status = "TIDAK LULUS";
        } else {
            grade = "E (Sangat Kurang)";
            status = "TIDAK LULUS";
        }

        // Kirim data ke ResultActivity via Intent
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            intent.putExtra("EXTRA_NAME", name);
            intent.putExtra("EXTRA_JURUSAN", jurusan);
            intent.putExtra("EXTRA_SCORE", score);
            intent.putExtra("EXTRA_GRADE", grade);
            intent.putExtra("EXTRA_STATUS", status);
            startActivity(intent);
        }
    }

    private void resetForm() {
        etStudentName.setText("");
        etStudentScore.setText("");
        etStudentName.setError(null);
        etStudentScore.setError(null);
        spJurusan.setSelection(0);
        etStudentName.requestFocus();
        if (getContext() != null) {
            Toast.makeText(getContext(), "Form telah direset", Toast.LENGTH_SHORT).show();
        }
    }
}
