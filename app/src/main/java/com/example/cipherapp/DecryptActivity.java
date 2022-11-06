package com.example.cipherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DecryptActivity extends AppCompatActivity {
    private final String TAG = "DecryptActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);

        EditText edTMessage = findViewById(R.id.edT_decrypt);

        Button btnReturn = findViewById(R.id.btn_decrypt_return2choice);
        btnReturn.setOnClickListener(view -> finish());

        Button btnDecrypt = findViewById(R.id.btn_decrypt_message);
        btnDecrypt.setOnClickListener(view -> {
            String message = edTMessage.getText().toString();
            Intent intent = new Intent(this,DecryptResultActivity.class);

            if (message.equals("")) // no message
                Toast.makeText(this, "Message is empty", Toast.LENGTH_SHORT).show();
            else { // decrypt the message
                Bundle extraInfo = new Bundle();
                for (int i = 1; i <= 25; i++) {
                    String result = getResultText(message, i);
                    extraInfo.putString("" + (26-i), result);
                }
                intent.putExtras(extraInfo);
                startActivity(intent);
            }
        });
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