package com.example.emailvalidator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button validateButton;
    private TextView validOrNot;
    private EditText emailAddressField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validateButton = findViewById(R.id.validateButton);
        validOrNot = findViewById(R.id.validOrNot);
        emailAddressField = findViewById(R.id.emailAddressField);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailAddressField.getText().toString().contains("@")){
                    validOrNot.setText("Valid");
                }
                else {
                    validOrNot.setText("Invalid");
                }
            }
        });
    }
}