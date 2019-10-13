package com.arsenic.coiny.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arsenic.coiny.Model.Discount;
import com.arsenic.coiny.R;

public class DiscountInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_info);

        Discount d = (Discount) getIntent().getSerializableExtra("Discount");



        TextView company = (TextView) findViewById(R.id.discinfo_company);
        company.setText(d.getCompany());

        TextView description = (TextView) findViewById(R.id.discinfo_desc);
        description.setText(d.getSmall_description());

        ImageView image = (ImageView) findViewById(R.id.discinfo_image);
        image.setImageResource(d.getImage_resource_id());



        ImageView upnav = (ImageView) findViewById(R.id.dis_upnav);
        upnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
