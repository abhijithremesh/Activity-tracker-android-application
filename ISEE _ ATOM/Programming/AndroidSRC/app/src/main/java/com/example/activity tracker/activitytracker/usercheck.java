package com.example.manish.activitytracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class usercheck extends Activity {

    Button Validate;
    EditText firstanswer, secondanswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usercheck);

        firstanswer = (EditText)findViewById(R.id.input_first_answer);
        secondanswer = (EditText)findViewById(R.id.input_second_answer);

        addListenerOnButton();

    }

    public void addListenerOnButton() {


        final Context context = this;
        Validate = (Button) findViewById(R.id.Validate_button);

        Validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if((firstanswer.getText().toString().equals("Batman"))&(secondanswer.getText().toString().equals("Berlin")))
                {
                    Intent intent = new Intent(context, CreatePIN.class);
                    startActivity(intent);
                }
                else
                {
                    Toast t =Toast.makeText(getApplicationContext(), "Invalid Entry",Toast.LENGTH_SHORT);
                    t.show();
                }




            }});


    }
}