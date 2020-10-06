package com.example.sse.lect2_taa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd, btnSub, btnMult, btnDiv, btnSqRt, btnEquals, btnDec, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private EditText txtView;
    private boolean checkadd, checksub, checkdiv, checkmult, checksquare;
    private float num1, num2;


private static final String MyFlag = "LFA";  //this will be our trail of breadcrumbs for logging events.

    public void buttonClick(Button button){
        String number =button.getText().toString();
        txtView.setText(txtView.getText().toString()+ number);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMult = (Button) findViewById(R.id.btnMult);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnSqRt = (Button) findViewById(R.id.btnSqRt);
        btnEquals = (Button) findViewById(R.id.btnEquals);
        btnDec = (Button) findViewById(R.id.btnDec);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        txtView = (EditText) findViewById(R.id.txtView);



        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(txtView.getText() + "0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(txtView.getText() + "1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(txtView.getText() + "2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(txtView.getText() + "3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(txtView.getText() + "4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(txtView.getText() + "5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(txtView.getText() + "6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(txtView.getText() + "7");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(txtView.getText() + "7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(txtView.getText() + "8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(txtView.getText() + "9");
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtView.getText().toString().contains("."))
                {

                }
                else {
                    txtView.setText(txtView.getText() + ".");
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkadd = true;
                num1 = Float.parseFloat(txtView.getText() + "");
                txtView.setText("");
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checksub = true;
                num1 = Float.parseFloat(txtView.getText() + "");
                txtView.setText("");
            }
        });

        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkmult = true;
                num1 = Float.parseFloat(txtView.getText() + "");
                txtView.setText("");
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkdiv = true;
                num1 = Float.parseFloat(txtView.getText() + "");
                txtView.setText("");
            }
        });

        btnSqRt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checksquare = true;
                num1 = Float.parseFloat(txtView.getText() + "");
                //txtView.setText("");
                txtView.setText(Math.sqrt(num1) + "");
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num2 = Float.parseFloat(txtView.getText() + "");
                if (checkadd == true)
                {
                    checkadd = false;
                    txtView.setText(num1 + num2 + "");
                }
                else if (checksub == true)
                {
                    checksub = false;
                    txtView.setText(num1 - num2 + "");
                }
                else if (checkmult == true)
                {
                    checkmult = false;
                    txtView.setText(num1 * num2 + "");
                }
                else if (checkdiv == true)
                {
                    checkdiv = false;
                    txtView.setText(num1 / num2 + "");
                }
                else if (checksquare == true)
                {
                    checksquare = false;
                    txtView.setText(Math.sqrt(num1) + "");
                }
            }
        });





    }

    @Override
    protected void onPause() {
        Log.i(MyFlag, "OnPause just called!");
        super.onPause();
    }
}
