package com.example.manish.activitytracker;

import android.app.Activity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class setsecurityqn extends Activity{

    EditText firstans, secondans;
    Button Save;
    String first,second;

    @Override   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setsecurityqn);

        firstans = (EditText)findViewById(R.id.input_first_answer);
        secondans = (EditText)findViewById(R.id.input_second_answer);


        addListenerOnButton();
    }

    public void addListenerOnButton()
    {
        final Context context = this;
        Save = (Button) findViewById(R.id.save_button);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

//                    first=firstans.getText().toString();
//                    second=secondans.getText().toString();
                Intent intent = new Intent(context, Profile.class);
                startActivity(intent);
                Toast t =Toast.makeText(getApplicationContext(), "The data has been saved",Toast.LENGTH_SHORT);
                t.show();

            }
        });

    }

}