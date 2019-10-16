package com.arsenic.coiny.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arsenic.coiny.DBController.DBManager;
import com.arsenic.coiny.Model.Contact;
import com.arsenic.coiny.Model.Usuario;
import com.arsenic.coiny.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class ContactPay extends AppCompatActivity {

    TextView montotv;
    TextInputEditText amount;
    DBManager db;
    Usuario u;
    Spinner categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_pay);

        db = new DBManager(this);

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String number = sp.getString("number", null);

        u = db.getUsuario(number);

        Contact c = (Contact) getIntent().getSerializableExtra("Contact");

        TextView initials = (TextView) findViewById(R.id.cpay_initials);
        initials.setText(c.getInitials());

        TextView nombre = (TextView) findViewById(R.id.cpay_nombre);
        nombre.setText(c.getNombre() + " " + c.getApellido());

        TextView numero = (TextView) findViewById(R.id.cpay_numero);
        numero.setText(c.getTelefono());

        categories = (Spinner) findViewById(R.id.categories_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.savings_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(adapter);

        ImageView upnav = (ImageView) findViewById(R.id.con_upnav);
        upnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        montotv = (TextView) findViewById(R.id.cpay_monto);

        amount = (TextInputEditText) findViewById(R.id.monto_input);
        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                montotv.setText("S/ " + amount.getText().toString());
            }
        });

        Button send = (Button) findViewById(R.id.cpay_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat df = new DecimalFormat("#.##");

                double am = Double.parseDouble(amount.getText().toString());

                double canpay = u.getSaldo_sol() - am;

                if(canpay >= 0.0){
                    //reducir saldo,
                    db.updateSaldo(u.getNumero(), (u.getSaldo_sol() - am), u.getSaldo_dol());

                    //aumentar ubudget si es que budget es > 0
                    String type =categories.getSelectedItem().toString().toLowerCase();
                    double alloc = db.getBudget(u.getNumero(), type);

                    if(alloc > 0.0) {
                        db.updateUsedBudget(u.getNumero(),type,am);
                    }

                    final ProgressDialog dialog = new ProgressDialog(ContactPay.this);

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
                    Toast.makeText(ContactPay.this, "No tienes saldo suficiente :c", Toast.LENGTH_SHORT ).show();
                }






            }
        });

    }
}
