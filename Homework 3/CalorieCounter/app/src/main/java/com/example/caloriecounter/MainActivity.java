package com.example.caloriecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText sandwichCount;
    private EditText ratatouilleCount;
    private EditText escargotCount;
    private TextView totalCal;
    private int sandCal = 500;
    private int rataCal = 300;
    private int escaCal = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sandwichCount = findViewById(R.id.sandwichCount);
        ratatouilleCount = findViewById(R.id.ratatouilleCount);
        escargotCount = findViewById(R.id.escargotCount);
        totalCal = findViewById(R.id.totalCal);


        sandwichCount.addTextChangedListener(new TextWatcher() {
            Double number;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(sandwichCount.getText().toString().length()>0) {
                    number = Double.parseDouble(sandwichCount.getText().toString());
                }
                else{
                    number = 0.0;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(sandwichCount.getText().toString().length()>0) {
                    number = Double.parseDouble(sandwichCount.getText().toString()) - number;

                }else{
                    number = 0.0 -number;
                }
                Double answer = Double.parseDouble(totalCal.getText().toString()) + (number * sandCal);
                totalCal.setText(answer.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        ratatouilleCount.addTextChangedListener(new TextWatcher() {
            Double number;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(ratatouilleCount.getText().toString().length()>0) {
                    number = Double.parseDouble(ratatouilleCount.getText().toString());
                }
                else{
                    number = 0.0;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ratatouilleCount.getText().toString().length()>0) {
                    number = Double.parseDouble(ratatouilleCount.getText().toString()) - number;

                }else{
                    number = 0.0 - number;
                }
                Double answer = Double.parseDouble(totalCal.getText().toString()) + (number * rataCal);
                totalCal.setText(answer.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        escargotCount.addTextChangedListener(new TextWatcher() {
            Double number;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(escargotCount.getText().toString().length()>0) {
                    number = Double.parseDouble(escargotCount.getText().toString());
                }
                else{
                    number = 0.0;
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(escargotCount.getText().toString().length()>0){
                    number = Double.parseDouble(escargotCount.getText().toString()) - number;

                }else{
                    number = 0.0 - number;
                }
                Double answer = Double.parseDouble(totalCal.getText().toString()) + (number * escaCal);
                totalCal.setText(answer.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}