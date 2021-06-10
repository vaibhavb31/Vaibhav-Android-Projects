package com.example.dbcontact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dbcontact.databinding.SpalshBinding;
import android.os.Handler;

public class SplashScreen extends Activity {
    Handler handler;
    SpalshBinding spalshBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spalshBinding= SpalshBinding.inflate(getLayoutInflater());
        View view= spalshBinding.getRoot();
        setContentView(view);



        handler=new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);

    }
}
