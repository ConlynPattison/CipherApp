package com.example.cipherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DecryptResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt_result);

        TextView tv1 = findViewById(R.id.tv_decrypt_result);
        Bundle extras = getIntent().getExtras();
        tv1.setText(combineResults(extras));

        Button btnReturn = findViewById(R.id.btn_decrypt_return);
        btnReturn.setOnClickListener(view -> finish());

    }

    private String combineResults(Bundle bundle) {
        String result = "Shift: Hidden Message\n\n";
        for (int i = 1; i <= 25; i++) {
            result = result.concat(String.format("%d: %s\n\n", i, bundle.getString(""+i)));
        }
        return result;
    }
}