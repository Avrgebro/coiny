package com.arsenic.coiny.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.arsenic.coiny.R;

public class Services extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);


        ImageView upnav = (ImageView) findViewById(R.id.services_upnav);
        upnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RelativeLayout agua = (RelativeLayout) findViewById(R.id.s_agua);
        RelativeLayout luz = (RelativeLayout) findViewById(R.id.s_luz);
        RelativeLayout tele = (RelativeLayout) findViewById(R.id.s_phone);

        agua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Services.this, ServicePay.class);
                intent.putExtra("type", "1");
                startActivity(intent);
            }
        });

        luz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Services.this, ServicePay.class);
                intent.putExtra("type", "2");
                startActivity(intent);

            }
        });

        tele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Services.this, ServicePay.class);
                intent.putExtra("type", "3");
                startActivity(intent);

            }
        });
    }
}
