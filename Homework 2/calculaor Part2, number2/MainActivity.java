package com.example.arithmeticprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnCalculate;
    private EditText txtOp1;
    private EditText txtOp2;
    private TextView lblAnswer;
    private Spinner operations;
    String[] operationList = new String[]{"+", "-", "/", "*","%"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, operationList);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtOp1 = findViewById(R.id.txtOp1);
        txtOp2 = findViewById(R.id.txtOp2);
        lblAnswer = findViewById(R.id.lblAnswer);
        operations = findViewById(R.id.operationSpinner);
        operations.setAdapter(adapter);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double Op1, Op2, Answer=0.0;
                String errMsg ="Invalid Operation";
                String operation;
                operation = operations.getSelectedItem().toString();;
                try {
                    Op1 = Double.parseDouble(txtOp1.getText().toString());
                    Op2 = Double.parseDouble(txtOp2.getText().toString());
                    if (operation == "+")
                    {
                        Answer = Op1 + Op2;
                    }
                    else if (operation == "-")
                    {
                        Answer = Op1 - Op2;
                    }
                    else if (operation == "/")
                    {
                        if(Op2==0){
                            errMsg ="Dividing by zero is not allowed";
                            throw new Exception();
                        }
                            Answer = Op1 / Op2;
                    }
                    else if (operation == "*")
                    {
                        Answer = Op1*Op2;
                    }
                    else{
                        if(Op2==0){
                            errMsg ="Mod by zero is not allowed";
                            throw new Exception();
                        }
                        Answer = Op1%Op2;
                    }
                    lblAnswer.setText(Answer.toString());
                }
                catch (Exception exception) {
                    Toast.makeText(getApplicationContext(),errMsg,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
