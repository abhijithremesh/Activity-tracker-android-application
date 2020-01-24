package com.example.manish.activitytracker;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.RelativeLayout;
import android.graphics.drawable.AnimationDrawable;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;
import android.view.inputmethod.InputMethodManager;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;


public class Login extends  Activity{

    Button SignUp, LogIn;
    EditText inputPIN, email;
    private Boolean Rememberme;
    private CheckBox RememberCheckBox;
    TextView TextCount;
    TextView TextForgot;
    String PIN;

    RelativeLayout container;
    AnimationDrawable anim1, anim2;
    ImageView img;


    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    String[] listItems;

    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        SignUp = (Button) findViewById(R.id.button_sign_up);
        inputPIN = (EditText)findViewById(R.id.editPIN);

        container = (RelativeLayout) findViewById(R.id.container);
        img = (ImageView)findViewById(R.id.imageview_app_logo);

        anim1 = (AnimationDrawable) container.getBackground();
        anim1.setEnterFadeDuration(6000);
        anim1.setExitFadeDuration(2000);

        anim2 = (AnimationDrawable) img.getBackground();
        anim2.setEnterFadeDuration(6000);
        anim2.setExitFadeDuration(2000);

        TextCount = (TextView)findViewById(R.id.count);
        TextCount.setVisibility(View.GONE);
        RememberCheckBox = (CheckBox)findViewById(R.id.checkbox_remember_me);
        loginPreferences =  getSharedPreferences("loginPrefs",Context.MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        Rememberme = loginPreferences.getBoolean("Remember me", false);
        if (Rememberme == true) {
            inputPIN.setText(loginPreferences.getString("PIN", ""));
            RememberCheckBox.setChecked(true);
        }



        addListenerOnButton();


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





    public void addListenerOnButton() {

        final Context context = this;
        SignUp = (Button) findViewById(R.id.button_sign_up);
        LogIn = (Button) findViewById(R.id.button_login);
        TextForgot=(TextView) findViewById(R.id.textview_forgot_password);



        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, Profile.class);
                startActivity(intent);
            }});

        TextForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, usercheck.class);
                startActivity(intent);

            }});

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == LogIn) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(inputPIN.getWindowToken(), 0);
                    PIN = inputPIN.getText().toString();
                    if (RememberCheckBox.isChecked())
                    {
                        loginPrefsEditor.putBoolean("RememberPIN", true);
                        loginPrefsEditor.putString("input PIN", PIN);
                        loginPrefsEditor.commit();
                    }
                    else
                    {
                        loginPrefsEditor.clear();
                        loginPrefsEditor.commit();
                    }
                }
                if(inputPIN.getText().toString().equals("1234"))
                {
                    Intent intent = new Intent(context, Homescreen.class);
                    startActivity(intent);
                }
                else
                {
                    Toast t =Toast.makeText(getApplicationContext(), "Wrong credentials",Toast.LENGTH_SHORT);
                    t.show();
                    TextCount.setVisibility(View.VISIBLE);
                    counter--;
                    TextCount.setText(Integer.toString(counter));

                    if (counter == 0) {
                        inputPIN.setEnabled(false);
                    }

                }

            }});

    }

}
