package com.example.manish.activitytracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DefaultScreen extends Activity {

    Button ContinuetoHome;
    private Button btnLogout;
    private Session session;

    RelativeLayout container;
    AnimationDrawable anim1, anim2;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_screen);

        addListenerOnButton();
        session = new Session(this);
        if(!session.loggedin()){
            logout();
        }
        btnLogout = (Button)findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


        container = (RelativeLayout) findViewById(R.id.container_default);
        img = (ImageView)findViewById(R.id.imageview_app_logo_default);

        anim1 = (AnimationDrawable) container.getBackground();
        anim1.setEnterFadeDuration(2000);
        anim1.setExitFadeDuration(2000);

        anim2 = (AnimationDrawable) img.getBackground();
        anim2.setEnterFadeDuration(2000);
        anim2.setExitFadeDuration(2000);

    }

    public void addListenerOnButton() {

        final Context context = this;
        ContinuetoHome = (Button) findViewById(R.id.button_continue_default);

        ContinuetoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, Homescreen.class);
                startActivity(intent);


            }
        });
    }

    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(DefaultScreen.this,LoginActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (anim1 != null && !anim1.isRunning())
            anim1.start();
        if (anim2 != null && !anim2.isRunning())
            anim2.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim1 != null && anim1.isRunning())
            anim1.stop();
        if (anim2 != null && anim2.isRunning())
            anim2.stop();
    }



}
