package com.cs591.fling2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity
        implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener
{

    private TextView txt;
    private GestureDetector GD;    //must instantiate the gesture detector


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GD = new GestureDetector(this, this);   //Context, Listener as per Constructor Doc.

        txt = (TextView)findViewById(R.id.txtMessage);
        txt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    GD.onTouchEvent(event);
                    return true;
            }
        });

    }

    public boolean onDoubleTap( MotionEvent e ) {
        // Log.w( MA, "Inside onDoubleTap" );
        return true;
    }

    public boolean onDoubleTapEvent( MotionEvent e ) {
        //Log.w( MA, "Inside onDoubleTapEvent" );
        return true;
    }

    public boolean onSingleTapConfirmed( MotionEvent e ) {
        // Log.w( MA, "Inside onSingleTapConfirmed" );
        return true;
    }





    @Override
    public boolean onDown(MotionEvent e) {
        //txt.setText("onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        //txt.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
//        txt.animate().y(txt.getY()+10);
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {

       //txt.setText("onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        //txt.setText("onLongPress");
    }
    public void txtRandom(){
        final DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //for randomly locating txt when threshold < velocity
        Random R = new Random();
        //get random value that depends on pixel of device
        final float dx = R.nextFloat() * (displaymetrics.widthPixels-100);
        final float dy = R.nextFloat() * (displaymetrics.heightPixels-100);
        txt.setX(dx);
        txt.setY(dy);
        Log.w("txtRandom","txtRandom x:" + dx +" and y: "+ dy );
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        final DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float moveX = e2.getRawX()-e1.getRawX();
        float moveY = e2.getRawY()-e1.getRawY();
        if(Math.abs(velocityX) > 5000 || Math.abs(velocityY) > 5000){
            //if true generate txt in random place
            txtRandom();
        }
        else{
            //we dont want the txtbox out of the frame.
            float maxX = displaymetrics.widthPixels-100;
            float maxY = displaymetrics.heightPixels-100;
            //if not animate the drag from beginning of the cursor to the end
            float finalx = e2.getRawX();
            float finaly = e2.getRawY();

            if (finalx< 0){
                finalx = 0;
            }if (finaly< 0){
                finaly=0;
            }if (finalx> maxX){
                finalx = maxX;
            }if (finaly>maxY) {
               finaly=maxY;
            }
            txt.animate().x(finalx).y(finaly);
        }

        long deltaTime = e2.getEventTime( ) - e1.getEventTime( );
        Log.w( "MA", "Inside onFling: deltaTime (in ms) = " + deltaTime );
        Log.w( "MA", "x1 = " + e1.getRawX( ) + "; y1 = " + e1.getRawY( ) );
        Log.w( "MA", "x2 = " + e2.getRawX( ) + "; y2 = " + e2.getRawY( ) );
        //Log.w( "MA", "measured vX (in pixels/second) = " + velocityX );
        //Log.w( "MA", "measured vY (in pixels/second) = " + velocityY );

        return true;
    }
}