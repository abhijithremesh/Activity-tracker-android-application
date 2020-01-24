package com.example.manish.activitytracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout;
import android.graphics.drawable.AnimationDrawable;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login, register;
    private EditText etEmail, etPass;
    private DbHelper db;
    private Session session;
    TextView TextCount;
    int counter = 5;

    LinearLayout container;
    AnimationDrawable anim1, anim2;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DbHelper(this);
        session = new Session(this);
        login = (Button)findViewById(R.id.btnLogin);
        register = (Button)findViewById(R.id.btnReg);
//        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        TextCount = (TextView)findViewById(R.id.count);
        TextCount.setVisibility(View.GONE);

        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this,DefaultScreen.class));
            finish();
        }

        container = (LinearLayout) findViewById(R.id.container);
        img = (ImageView)findViewById(R.id.imageview_app_logo1);

        anim1 = (AnimationDrawable) container.getBackground();
        anim1.setEnterFadeDuration(6000);
        anim1.setExitFadeDuration(2000);

        anim2 = (AnimationDrawable) img.getBackground();
        anim2.setEnterFadeDuration(6000);
        anim2.setExitFadeDuration(2000);



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnReg:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            default:

        }
    }

    private void login(){

        String pass = etPass.getText().toString();

        if(db.getUser(pass)){
            session.setLoggedin(true);
            startActivity(new Intent(LoginActivity.this, DefaultScreen.class));
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Wrong credentials",Toast.LENGTH_SHORT).show();
            TextCount.setVisibility(View.VISIBLE);
            counter--;
            TextCount.setText(Integer.toString(counter));
            if (counter == 0) {
                etPass.setEnabled(false);
            }
        }
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
