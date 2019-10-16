package com.arsenic.coiny.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.arsenic.coiny.R;
import com.mancj.slimchart.SlimChart;

public class BudgetInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_info);

        ImageView upnav = (ImageView) findViewById(R.id.binfo_upnav);
        upnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SlimChart slimChart = (SlimChart) findViewById(R.id.slimChart);

        //Optional - create colors array
        int[] colors = new int[4];
        colors[0] = R.color.colorPrimary;
        colors[1] = R.color.colorAccent;
        //slimChart.setColors(colors);

        final float[] stats = new float[4];
        stats[0] = 100;
        stats[1] = 85;

        slimChart.setStats(stats);

        slimChart.setColor(ContextCompat.getColor(this, R.color.colorPrimary));

        //Play animation
        slimChart.setStartAnimationDuration(2000);


    }
}

