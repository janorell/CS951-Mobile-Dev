package com.example.c4_p33;

// primary reference https://csab373.appspot.com/CSP_AS/unit?unit=117&lesson=118

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String [] LetterArray = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private static final String LetterString = "abcdefghijklmnopqrstuvwxyz";
    private Button shiftButton;
    private EditText plainText;
    private TextView currentShift;
    private Button encryptButton;
    private int shift = 1;
    private String input;
    private TextView encryptedText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentShift = findViewById(R.id.currentShift);
        plainText = findViewById(R.id.plainText);
        encryptedText = findViewById(R.id.encryptedText);
        shiftButton = findViewById(R.id.btn_shift);
        // move to shiftPage
        shiftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input = plainText.getText().toString();
                // input 2 params for the bundle
                shift_page(input,shift);
            }
        });
        Pattern p = Pattern.compile("[a-z]");
        Matcher m = p.matcher(plainText.getText().toString());
        encryptButton = findViewById(R.id.encryptButton);
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Encrypt", "Button Pressed");
                try {
                    // try encryption
                    encrypt();
                }
                catch(Exception e){
                    // else input is not correct
                    Toast.makeText(MainActivity.this, "input not correct", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // try and check if this is the first call
        try {
            // if bundle is not null, get the bundle and fill the textView
            Bundle bundle = getIntent().getExtras();
            // keeping track of user input in the first activity
            input = bundle.getString("input");
            shift = bundle.getInt("shift");
            Toast.makeText(this, "input is : "+ input+" shift is :" + shift, Toast.LENGTH_SHORT).show();
            plainText.setText(input);
            currentShift.setText(shift+"");
        }catch (Exception e){
            Toast.makeText(this, "First", Toast.LENGTH_SHORT).show();
        }
    }


    private void shift_page(String input,int shift) {
        Intent intent = new Intent(this, setshift.class);
        // creating bundle to send it over to other activity
        Bundle bundle = new Bundle();
        bundle.putString("input", input);
        bundle.putInt("shift", shift);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void encrypt(){
        String answer = "";
        String decryptText = plainText.getText().toString().replaceAll("\\s+","").toLowerCase();
        ArrayList<String> _shifted = new ArrayList<>();

        // get shift number
        int _shift = loadShift();

        // create shifted array with said shift number
        _shifted = createShiftedAlphabet(_shift);

        for(int i=0; i<decryptText.length(); ++i){
            Character character = decryptText.charAt(i);
            String s_Char = character.toString();
            int Index = LetterString.indexOf(s_Char);
            System.out.println(_shifted.get(Index));
            answer = answer.concat(_shifted.get(Index));
            System.out.println(answer);
        }
        encryptedText.setText(answer);
    }

    private int loadShift() {
        int _shift = 0;

        // check if shift box is empty
        if(TextUtils.isEmpty(currentShift.getText().toString())){
            Toast.makeText(MainActivity.this, "Shift field cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else{
            _shift = Integer.parseInt(currentShift.getText().toString());
        }

        // check for proper shift values
        if(_shift > 25 || _shift < 0) {
            Toast.makeText(MainActivity.this, "Illegal shift number: " + _shift, Toast.LENGTH_SHORT ).show();
            _shift = 0;
        }
        return _shift;
    }

    private ArrayList<String> createShiftedAlphabet(int shift){
        int temp = shift;

        // make copy of the alphabet
        ArrayList<String> _shiftedAlphabet = new ArrayList<>(Arrays.asList(LetterArray));

        // create shifted alphabet
        while(temp > 0){
            String _s =_shiftedAlphabet.remove(0);
            _shiftedAlphabet.add(_s);
            --temp;
        }
        Log.d("createShiftedAlphabet: ", _shiftedAlphabet.toString() + "Shift: " + shift);
        return _shiftedAlphabet;
    }
}
