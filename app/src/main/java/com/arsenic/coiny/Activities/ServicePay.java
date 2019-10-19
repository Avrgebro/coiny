package com.arsenic.coiny.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arsenic.coiny.R;

public class ServicePay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_pay);

        ImageView upnav = (ImageView) findViewById(R.id.spay_upnav);
        upnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String type = getIntent().getStringExtra("type");

        TextView title = (TextView) findViewById(R.id.spay_title);
        TextView amount = (TextView) findViewById(R.id.spay_amt);
        Spinner spnr = (Spinner) findViewById(R.id.cpay_spinner);
        final CheckBox chbx = (CheckBox) findViewById(R.id.chbx);
        ImageView img = (ImageView) findViewById(R.id.imageView3);
        final RelativeLayout sp = (RelativeLayout) findViewById(R.id.spay_detail);

        ArrayAdapter<CharSequence> adapter;

        spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sp.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        switch (type){
            case "1":
                img.setImageResource(R.drawable.drop);

                adapter = ArrayAdapter.createFromResource(this,
                        R.array.service_water, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnr.setAdapter(adapter);

                title.setText("Recibo Agua");

                amount.setText("S/ 36.80");

                break;

            case "2":
                img.setImageResource(R.drawable.luz);

                adapter = ArrayAdapter.createFromResource(this,
                        R.array.service_light, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnr.setAdapter(adapter);

                title.setText("Recibo Luz");

                amount.setText("S/ 46.70");

                break;

            case "3":
                img.setImageResource(R.drawable.telefono);

                adapter = ArrayAdapter.createFromResource(this,
                        R.array.service_phone, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnr.setAdapter(adapter);

                title.setText("Recibo Móvil");

                amount.setText("S/ 79.90");

                break;
        }

        Button btn = (Button) findViewById(R.id.spay_send);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(chbx.isChecked()){
                    final ProgressDialog dialog = new ProgressDialog(ServicePay.this);

                    dialog.setMessage("Estamos enviando tu pago!!");
                    dialog.show();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            dialog.dismiss();
                            finish();
                        }
                    }, 2000);

                } else {
                    Toast.makeText(ServicePay.this, "Debe seleccionar un recibo", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
