package com.example.alfian04;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;

public class CalculatorFragment extends Fragment {

    private EditText etNum1;
    private EditText etNum2;
    private TextView tvSelectedOp;
    private TextView tvCalcResult;

    private String selectedOperator = "+";

    public CalculatorFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        etNum1 = view.findViewById(R.id.et_num1);
        etNum2 = view.findViewById(R.id.et_num2);
        tvSelectedOp = view.findViewById(R.id.tv_selected_op);
        tvCalcResult = view.findViewById(R.id.tv_calc_result);

        Button btnAdd = view.findViewById(R.id.btn_op_add);
        Button btnSub = view.findViewById(R.id.btn_op_sub);
        Button btnMul = view.findViewById(R.id.btn_op_mul);
        Button btnDiv = view.findViewById(R.id.btn_op_div);

        Button btnCalculate = view.findViewById(R.id.btn_calculate);
        Button btnClear = view.findViewById(R.id.btn_clear_calc);

        btnAdd.setOnClickListener(v -> setOperator("+", "Penjumlahan"));
        btnSub.setOnClickListener(v -> setOperator("-", "Pengurangan"));
        btnMul.setOnClickListener(v -> setOperator("×", "Perkalian"));
        btnDiv.setOnClickListener(v -> setOperator("÷", "Pembagian"));

        btnCalculate.setOnClickListener(v -> calculate());
        btnClear.setOnClickListener(v -> clearCalculator());

        return view;
    }

    private void setOperator(String op, String label) {
        selectedOperator = op;
        tvSelectedOp.setText(String.format("Operator Terpilih: (%s) %s", op, label));
    }

    private void calculate() {
        String num1Str = etNum1.getText().toString().trim();
        String num2Str = etNum2.getText().toString().trim();

        // Validasi input kosong
        if (TextUtils.isEmpty(num1Str)) {
            etNum1.setError(getString(R.string.err_empty_calc_num));
            etNum1.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(num2Str)) {
            etNum2.setError(getString(R.string.err_empty_calc_num));
            etNum2.requestFocus();
            return;
        }

        double num1;
        double num2;

        try {
            num1 = Double.parseDouble(num1Str);
        } catch (NumberFormatException e) {
            etNum1.setError("Angka pertama tidak valid");
            etNum1.requestFocus();
            return;
        }

        try {
            num2 = Double.parseDouble(num2Str);
        } catch (NumberFormatException e) {
            etNum2.setError("Angka kedua tidak valid");
            etNum2.requestFocus();
            return;
        }

        double result = 0.0;

        switch (selectedOperator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "×":
                result = num1 * num2;
                break;
            case "÷":
                // Validasi pembagian dengan 0
                if (num2 == 0.0) {
                    etNum2.setError(getString(R.string.err_divide_by_zero));
                    etNum2.requestFocus();
                    tvCalcResult.setText("Error (÷ 0)");
                    if (getContext() != null) {
                        Toast.makeText(getContext(), getString(R.string.err_divide_by_zero), Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                result = num1 / num2;
                break;
            default:
                result = num1 + num2;
                break;
        }

        DecimalFormat df = new DecimalFormat("#.######");
        tvCalcResult.setText(df.format(result));
    }

    private void clearCalculator() {
        etNum1.setText("");
        etNum2.setText("");
        etNum1.setError(null);
        etNum2.setError(null);
        tvCalcResult.setText(getString(R.string.default_calc_result));
        setOperator("+", "Penjumlahan");
        etNum1.requestFocus();
        if (getContext() != null) {
            Toast.makeText(getContext(), "Kalkulator telah dibersihkan", Toast.LENGTH_SHORT).show();
        }
    }
}
