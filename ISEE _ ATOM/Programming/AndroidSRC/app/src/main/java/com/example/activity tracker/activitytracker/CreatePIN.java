package com.example.manish.activitytracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePIN extends Activity {

    Button ContinuetoHome;
    EditText SetPIN;
    EditText ConfirmPIN;
    public String finalpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createpin);

        SetPIN = (EditText)findViewById(R.id.enter_pin);
        ConfirmPIN = (EditText)findViewById(R.id.confirm_pin);

        addListenerOnButton();

    }

    public void addListenerOnButton() {

        final Context context = this;
        ContinuetoHome = (Button) findViewById(R.id.continue_PINtoHome);

        ContinuetoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if(SetPIN == null && ConfirmPIN == null)

                {
                    Toast t =Toast.makeText(getApplicationContext(), " Please enter values ",Toast.LENGTH_SHORT);
                    t.show();

                }

                else if(SetPIN.getText().toString().equals(ConfirmPIN.getText().toString())&& (SetPIN != null && ConfirmPIN != null))
                {
                    finalpin=ConfirmPIN.getText().toString();
                    Toast t =Toast.makeText(getApplicationContext(), "PIN has been set",Toast.LENGTH_SHORT);
                    t.show();
                    Intent intent = new Intent(context, Homescreen.class);
                    startActivity(intent);
                }
                else
                {
                    Toast t =Toast.makeText(getApplicationContext(), "Entries not matching!!",Toast.LENGTH_SHORT);
                    t.show();
                }




            }});

    }

}
