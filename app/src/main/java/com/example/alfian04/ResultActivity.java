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
        TextView tvStatus = findViewById(R.id.tv_res_status);

        Button btnBack = findViewById(R.id.btn_res_back);
        Button btnHome = findViewById(R.id.btn_res_home);

        // Ambil data dari Intent Extras
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("EXTRA_NAME");
            String jurusan = intent.getStringExtra("EXTRA_JURUSAN");
            double score = intent.getDoubleExtra("EXTRA_SCORE", 0.0);
            String grade = intent.getStringExtra("EXTRA_GRADE");
            String status = intent.getStringExtra("EXTRA_STATUS");

            tvName.setText(name != null ? name : "-");
            tvJurusan.setText(jurusan != null ? jurusan : "-");
            tvScore.setText(String.valueOf(score));
            tvGrade.setText(grade != null ? grade : "-");
            
            if (status != null) {
                tvStatus.setText(status);
                if (status.equalsIgnoreCase("LULUS")) {
                    tvStatus.setBackgroundColor(ContextCompat.getColor(this, R.color.success));
                } else {
                    tvStatus.setBackgroundColor(ContextCompat.getColor(this, R.color.error));
                }
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
