/*
    W4_P2 - Currency Converter
    This program takes in as input an amount of euros and converts the amount into 4 different
    currencies: US Dollar, JP Yen, GB Pound and BTC Bitcoin.
 */

package com.example.w4_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
    MainActivity - the only main activity for this app. This screen shows all of the conversions.
 */
public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    //Declaring all of the TextViews that display the currency amounts
    private EditText euro;
    private TextView usd;
    private TextView yen;
    private TextView pound;
    private TextView btc;
    private GestureDetector GD;

    //Conversion rates for euro to four other currencies as of 10/3
    final double eurotousd = 1.17;
    final double eurotoyen = 123.38;
    final double eurotopound = 0.91;
    final double eurotobtc = 0.00011;

    //the current value in the euro textbox
    double current_euro = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //allows us to use gestures in this app
        GD = new GestureDetector(this, this);
        GD.setOnDoubleTapListener(this);

        euro = (EditText) findViewById(R.id.euro);
        //watches for changes in the euro textbox so that users can manually enter an amount
        euro.addTextChangedListener(euroWatcher);

        usd = (TextView) findViewById(R.id.usd);
        yen = (TextView) findViewById(R.id.yen);
        pound = (TextView) findViewById(R.id.pound);
        btc = (TextView) findViewById(R.id.btc);

        //initially set euro amount of 0
        euro.setText("0.00");
    }

    private TextWatcher euroWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        //monitors for changes in the euro textbox in case a user enters an amount manually
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            //try catch because euro amount can't be null, if it is, a toast pops up asking the
            //user to enter a value
            try{
                String euroVal = s.toString();
                current_euro = Double.parseDouble(euroVal);
                //user enters a negative currency amount which obviously isn't correct, so
                //display a toast asking them to enter a positive amount
                if (current_euro < 0){
                    Toast.makeText(getApplicationContext(),"Positive values only.",Toast.LENGTH_LONG).show();
                    current_euro = 0;
                    euro.setText("0.0");
                }
                //user enters a positive currency amount so we can multiply the amount of euros
                //they entered by the conversion rate to get the amount in other currencies
                else{
                    usd.setText("USD $" + String.format("%.2f", current_euro * eurotousd));
                    yen.setText("JPY ¥" + String.format("%.2f", current_euro * eurotoyen));
                    pound.setText("GBP £" + String.format("%.2f", current_euro * eurotopound));
                    btc.setText("BTC ₿" + String.format("%.2f", current_euro * eurotobtc));
                }
            }
            catch(NumberFormatException ex){
                Toast.makeText(getApplicationContext(),"Please enter an amount.",Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.GD.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //the user is scrolling up
        if (distanceY > 0){
            //incrementing by 5 cents on current euro amount
            current_euro = Double.parseDouble(euro.getText().toString()) + 0.05;
            euro.setText(String.format("%.2f", current_euro));
        }
        //the user is scrolling down
        else if (distanceY < 0) {
            //decreasing by 5 cents on current euro amount
            current_euro = Double.parseDouble(euro.getText().toString()) - 0.05;
            euro.setText(String.format("%.2f", current_euro));
        }
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //the user is flinging down
        if (velocityY > 0){
            //increasing by 1 euro on current euro amount
            current_euro = Double.parseDouble(euro.getText().toString()) - 1;
            euro.setText(String.format("%.2f", current_euro));
        }
        //the user is flinging up
        else if (velocityY < 0) {
            //decreasing by 1 euro on current euro amount
            current_euro = Double.parseDouble(euro.getText().toString()) + 1;
            euro.setText(String.format("%.2f", current_euro));
        }
        return true;
    }

    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return true;
    }

}