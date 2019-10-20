package com.arsenic.coiny.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arsenic.coiny.Activities.Authentication.Register;
import com.arsenic.coiny.DBController.DBManager;
import com.arsenic.coiny.MainActivity;
import com.arsenic.coiny.Model.Usuario;
import com.arsenic.coiny.R;
import com.google.android.material.textfield.TextInputEditText;

public class Exchange extends AppCompatActivity {

    double tc = 1/3.35;
    int current_tc = 1;//1 es sol / 0 es dolar

    TextView c1text;
    TextView c2text;
    TextInputEditText c1value;
    TextInputEditText c2value;

    ImageView swap;
    Button btn;

    DBManager db;

    Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        db = new DBManager(this);

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String number = sp.getString("number", null);

        u = db.getUsuario(number);

        ImageView upnav = (ImageView) findViewById(R.id.exchange_upnav);
        upnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        c1text = (TextView) findViewById(R.id.c1_text);
        c2text = (TextView) findViewById(R.id.c2_text);

        c1value = (TextInputEditText) findViewById(R.id.c1_value);
        c2value = (TextInputEditText) findViewById(R.id.c2_value);

        swap = (ImageView) findViewById(R.id.swap);

        btn = (Button) findViewById(R.id.exchange_btn);

        c1value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double amount;
                if(s.toString().length() == 0){
                    amount = 0.0;
                } else {
                    amount = Double.parseDouble(s.toString());
                }
                double conversion = amount * tc;

                double aux = Math.floor(conversion * 100) / 100;
                c2value.setText(aux +"");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aux = c1text.getText().toString();
                c1text.setText(c2text.getText().toString());
                c2text.setText(aux);

                tc = 1.0 / tc;

                current_tc = (current_tc == 1) ? 0 : 1;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = new ProgressDialog(Exchange.this);

                dialog.setMessage("Estamos actualizando tu saldo!!");
                dialog.show();

                double ssol = (current_tc == 1) ? Double.parseDouble(c1value.getText().toString()) : Double.parseDouble(c2value.getText().toString());
                double sdol = (current_tc == 0) ? Double.parseDouble(c1value.getText().toString()) : Double.parseDouble(c2value.getText().toString());

                double deduct = (current_tc == 1) ? ssol : sdol;
                double increase = (current_tc == 0) ? ssol : sdol;

                double available = (current_tc == 1) ? u.getSaldo_sol() : u.getSaldo_dol();

                if(deduct > available) {
                    dialog.dismiss();
                    Toast.makeText(Exchange.this, "No tienes saldo suficiente :c", Toast.LENGTH_SHORT).show();
                } else {
                    if(current_tc == 1){
                        db.updateSaldo(u.getNumero(), (available - deduct), u.getSaldo_dol() + increase );
                    } else {
                        db.updateSaldo(u.getNumero(),  u.getSaldo_sol() + increase, (available - deduct) );
                    }

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            dialog.dismiss();
                            finish();
                        }
                    }, 2000);


                }




            }
        });



    }
}
