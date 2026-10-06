package com.example.alfian04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvName = findViewById(R.id.tv_res_name);
        TextView tvJurusan = findViewById(R.id.tv_res_jurusan);
        TextView tvScore = findViewById(R.id.tv_res_score);
        TextView tvGrade = findViewById(R.id.tv_res_grade);
        TextView tvGradeDesc = findViewById(R.id.tv_res_grade_desc);
        TextView tvStatus = findViewById(R.id.tv_res_status);

        Button btnBack = findViewById(R.id.btn_res_back);
        Button btnHome = findViewById(R.id.btn_res_home);

        // Ambil data dari Intent Extras
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("EXTRA_NAME");
            String jurusan = intent.getStringExtra("EXTRA_JURUSAN");
            double score = intent.getDoubleExtra("EXTRA_SCORE", 0.0);
            String gradeLetter = intent.getStringExtra("EXTRA_GRADE_LETTER");
            String gradeDesc = intent.getStringExtra("EXTRA_GRADE_DESC");
            String grade = intent.getStringExtra("EXTRA_GRADE");
            String status = intent.getStringExtra("EXTRA_STATUS");

            // Kompatibilitas: pecah "A (Sangat Baik)" jadi huruf + keterangan
            if ((gradeLetter == null || gradeDesc == null) && grade != null) {
                int open = grade.indexOf('(');
                if (open > 0) {
                    gradeLetter = grade.substring(0, open).trim();
                    int close = grade.indexOf(')', open);
                    gradeDesc = close > open
                            ? grade.substring(open + 1, close).trim()
                            : grade.substring(open + 1).trim();
                } else {
                    gradeLetter = grade.trim();
                    gradeDesc = "";
                }
            }

            tvName.setText(name != null ? name : "-");
            tvJurusan.setText(jurusan != null ? jurusan : "-");
            // Tampilkan 90 bukan 90.0 agar rapi
            if (score == Math.rint(score)) {
                tvScore.setText(String.valueOf((long) score));
            } else {
                tvScore.setText(String.valueOf(score));
            }
            tvGrade.setText(gradeLetter != null && !gradeLetter.isEmpty() ? gradeLetter : "-");
            tvGradeDesc.setText(gradeDesc != null && !gradeDesc.isEmpty() ? gradeDesc : "Hasil Penilaian");
            
            if (status != null) {
                tvStatus.setText(status);
                int color = status.equalsIgnoreCase("LULUS")
                        ? ContextCompat.getColor(this, R.color.success)
                        : ContextCompat.getColor(this, R.color.error);
                tvStatus.setBackgroundTintList(android.content.res.ColorStateList.valueOf(color));
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.white));
            } else {
                tvStatus.setText("-");
            }
        }

        // Tombol Kembali
        btnBack.setOnClickListener(v -> finish());

        // Tombol Kembali ke Home
        btnHome.setOnClickListener(v -> {
            Intent homeIntent = new Intent(ResultActivity.this, MainActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(homeIntent);
            finish();
        });
    }
}
