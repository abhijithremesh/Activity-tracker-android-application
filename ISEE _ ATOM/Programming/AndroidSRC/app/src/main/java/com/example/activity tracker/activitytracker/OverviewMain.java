package com.example.manish.activitytracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OverviewMain extends Activity {


    Button CustomOverview, DailyOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_main);

        DailyOverview = (Button)findViewById(R.id.button_daily_overview);
        CustomOverview = (Button)findViewById(R.id.button_custom_overview);

        addListenerOnButton();


    }

    public void addListenerOnButton() {

        final Context context = this;

        DailyOverview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {

                        Intent intent = new Intent(context, OverviewDate.class);
                        startActivity(intent);

                    }
        });

        CustomOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, OverviewCustomDate.class);
                startActivity(intent);

            }
        });



    }

}
