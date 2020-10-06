package com.example.w4_p1;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.Manifest;
import android.widget.Toast;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    private Switch lightSwitch;
    private EditText txtCmd;
    private CameraManager mCameraManager;
    private String mCameraId;
    private GestureDetector GD;
    private LinearLayout llay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GD = new GestureDetector(this, this);
        GD.setOnDoubleTapListener(this);



        boolean isFlashAvailable = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!isFlashAvailable) {
            Toast.makeText(getApplicationContext(),"No Light, This won't work ://",Toast.LENGTH_LONG).show();
            return;
        }
 

        //lightSwitch = (Switch) findViewById(R.id.lightSwitch);
        lightSwitch = new Switch(this); // dynamically rendered
        lightSwitch.setText("Flashlight");
        lightSwitch.setChecked(false);
        txtCmd = (EditText) findViewById(R.id.txtCmd);
        llay = (LinearLayout) findViewById(R.id.llay);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        llay.addView(lightSwitch, lp);

        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE); // setting up flashlight permissions
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { // for the switch

                if (lightSwitch.isChecked())
                {
                    txtCmd.setText("ON"); //
                    try {
                        mCameraManager.setTorchMode(mCameraId, true); // this is where the light will be turned all, all other ways to turn it will reference this
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }

                }
                else if (!lightSwitch.isChecked())
                {
                    txtCmd.setText("OFF");
                    try {
                        mCameraManager.setTorchMode(mCameraId, false); // turn light off
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        txtCmd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (txtCmd.getText().toString().equalsIgnoreCase("ON")) // connecting text to switch
                {
                    lightSwitch.setChecked(true); // if on is written, turns light on
                }
                else if (txtCmd.getText().toString().equalsIgnoreCase("OFF"))
                {
                    lightSwitch.setChecked(false); // if off is written, turns light off
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.GD.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }


    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) { // connecting fling gesture to light
        if (v1 < 0) {
            lightSwitch.setChecked(true); // fling up to turn light on
        }
        else if (v1 > 0)
        {
            lightSwitch.setChecked(false); // fling down to turn light off
        }

        return true;
    }
}