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

    private Button btnCompare;
    private EditText txtPassword;
    private EditText txtConfirmPassword;
    private TextView lblAnswer;


private static final String MyFlag = "LFA";  //this will be our trail of breadcrumbs for logging events.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(MyFlag, "OnCreate just called!");
        super.onCreate(savedInstanceState);
//        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        btnCompare = (Button) findViewById(R.id.btnCompare);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        lblAnswer = (TextView) findViewById(R.id.lblAnswer);

        btnCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Answer = "Answer";

            if(txtPassword.getText().toString().equals(txtConfirmPassword.getText().toString())){
                Answer = "Match";
            }
            else {
                Answer = "Not a Match";
            }
            lblAnswer.setText(Answer);
            }
        });
    }

    @Override
    protected void onPause() {
        Log.i(MyFlag, "OnPause just called!");
        super.onPause();
    }
}
