package com.example.c4_p33;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class setshift extends AppCompatActivity {
    private int shift;
    private Button back;
    private Button setShift;
    private TextView shiftTxt;
    private String input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        // keeping track of user input in the first activity
        input = bundle.getString("input");
        shift = bundle.getInt("shift");
        Toast.makeText(this, "input is : "+ input+" shift is :" + shift, Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_setshift);
        shiftTxt = findViewById(R.id.shiftTxt);
        setShift = findViewById(R.id.btn_setShift);
        shiftTxt.setText(shift+"");
        back = findViewById(R.id.btn_goback);
        // on clicking back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // re-creates main and sends shift # back
                goBack(input,shift);
            }
        });
        setShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // make sure shift is in right range
                if(Integer.parseInt(shiftTxt.getText().toString()) >25 || Integer.parseInt(shiftTxt.getText().toString())<1){
                    Toast.makeText(setshift.this, "please select a number between 1 to 25 ", Toast.LENGTH_SHORT).show();
                }else{
                    // set shift to input number
                    shift = Integer.parseInt(shiftTxt.getText().toString());
                    Toast.makeText(setshift.this, "shift is set to " + shift, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void goBack(String input,int shift) {
        Intent intent = new Intent(this, MainActivity.class);
        // creating bundle to send it over to other activity
        Bundle bundle = new Bundle();
        bundle.putString("input",input);
        bundle.putInt("shift", shift);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
