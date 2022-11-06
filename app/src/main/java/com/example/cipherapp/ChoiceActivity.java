package com.example.cipherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity {
    private final String TAG = "ChoiceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Button btnEncrypt = findViewById(R.id.btn_encrypt);
        btnEncrypt.setOnClickListener(view -> startActivity(new Intent(this, EncryptActivity.class)));

        Button btnDecrypt = findViewById(R.id.btn_decrypt);
        btnDecrypt.setOnClickListener(view -> startActivity(new Intent(this, DecryptActivity.class)));

    }
}