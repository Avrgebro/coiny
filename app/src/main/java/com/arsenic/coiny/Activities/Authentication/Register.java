package com.arsenic.coiny.Activities.Authentication;

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
import android.widget.ImageView;
import android.widget.Toast;

import com.arsenic.coiny.DBController.DBManager;
import com.arsenic.coiny.MainActivity;
import com.arsenic.coiny.R;
import com.cooltechworks.creditcarddesign.CreditCardView;
import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {

    CreditCardView ccard;
    TextInputEditText nombre;
    TextInputEditText apeliido;
    TextInputEditText numero;

    DBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DBManager(this);

        ImageView upnav = (ImageView) findViewById(R.id.register_upnav);
        upnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        Login.class);
                startActivity(intent);
                finish();
            }
        });

        ccard = (CreditCardView) findViewById(R.id.card_5);
        numero = (TextInputEditText) findViewById(R.id.register_num);


        TextInputEditText cardn = (TextInputEditText) findViewById(R.id.register_cardn);

        cardn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ccard.setCardNumber(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        nombre = (TextInputEditText) findViewById(R.id.register_nom);
        apeliido = (TextInputEditText) findViewById(R.id.register_ape);

        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ccard.setCardHolderName(s.toString() + " " + apeliido.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        apeliido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ccard.setCardHolderName(nombre.getText().toString() + " " + s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button rgbtn = (Button) findViewById(R.id.register_rg);

        rgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom = nombre.getText().toString();
                String ape = apeliido.getText().toString();
                String num = numero.getText().toString();
                String cnum = ccard.getCardNumber();

                if(nom.isEmpty() || ape.isEmpty() || num.isEmpty() || cnum.isEmpty()){
                    Toast.makeText(Register.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    db.insertRecord(nom, ape, 123456, num, 1000);

                    SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();

                    editor.putInt("user_logged", 1);
                    editor.putString("number", num);

                    editor.apply();

                    final ProgressDialog dialog = new ProgressDialog(Register.this);

                    dialog.setMessage("Estamos registrando tus datos!!");
                    dialog.show();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            dialog.dismiss();
                            Intent intent = new Intent(getApplicationContext(),
                                    MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 2000);


                }


            }
        });
    }
}
