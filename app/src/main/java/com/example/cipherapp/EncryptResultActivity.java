package com.example.cipherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class EncryptResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_result);

        TextView tv1 = findViewById(R.id.tv1);
        Button btnReturn = findViewById(R.id.btnReturn);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String result = extras.getString("result");
            if (result != null)
                tv1.setText(result);
        }

        btnReturn.setOnClickListener(view -> finish());
    }
}