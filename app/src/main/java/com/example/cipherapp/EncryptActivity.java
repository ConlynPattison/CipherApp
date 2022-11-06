package com.example.cipherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EncryptActivity extends AppCompatActivity {
    private final String TAG = "EncryptActivity";
    private static final int MIN_SHIFT = 1;
    private static final int MAX_SHIFT = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);

        EditText edTCipherInput = findViewById(R.id.edT1);
        EditText edTCipherShift = findViewById(R.id.edT2);
        Button btnSubmit = findViewById(R.id.btn1);
        Button btnReturn = findViewById(R.id.btn_encrypt_return2choice);

        btnReturn.setOnClickListener(view -> finish());

        btnSubmit.setOnClickListener(view -> {
            String result = "";
            int shiftValue = 0;
            String inputString = edTCipherInput.getText().toString();
            String shiftString = edTCipherShift.getText().toString();
            Intent intent = new Intent(this, EncryptResultActivity.class);
            Bundle extraInfo = new Bundle();

            if (!shiftString.equals("")) {
                try {
                    shiftValue = Integer.parseInt(edTCipherShift.getText().toString());
                } catch (NumberFormatException e) {
                    Log.wtf(TAG, "String->Integer parse error: shiftValue");
                }
            }

            if (!validShift(shiftValue))
                Toast.makeText(this, "Invalid shift value...\nMust be between 0 and 26 (exclusive)", Toast.LENGTH_SHORT).show();
            else {
                if (!inputString.equals(""))
                    result = getResultText(inputString, shiftValue);
                if (!result.equals(""))
                    extraInfo.putString("result", result);
                intent.putExtras(extraInfo);
                startActivity(intent);
            }
        });
    }

    private boolean validShift(int shiftValue) {
        return (shiftValue >= MIN_SHIFT && shiftValue <= MAX_SHIFT);
    }

    private String getResultText(String inputString, int shiftValue) {
        String result = "";
        for (int i = 0; i < inputString.length(); i++) {
            char character = inputString.charAt(i);
            int asciiInt, asciiIntShifted, min = -1, max = -1;
            boolean valid = true;

            asciiInt = (int) character;
            if (asciiInt >= 65 && asciiInt <= 90) {
                min = 65;
                max = 90;
            } else if (asciiInt >= 97 && asciiInt <= 122) {
                min = 97;
                max = 122;
            } else {
                valid = false;
            }

            if (valid) {
                asciiIntShifted = asciiInt + shiftValue;
                if (asciiIntShifted > max)
                    asciiIntShifted = (min - 1) + (asciiIntShifted - max);
                result = String.format("%s%s", result, (char) asciiIntShifted);
            } else
                result = String.format("%s%s", result, character);
        }
        return result;
    }
}