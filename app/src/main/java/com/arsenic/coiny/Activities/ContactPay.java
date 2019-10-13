package com.arsenic.coiny.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.arsenic.coiny.Model.Contact;
import com.arsenic.coiny.R;

public class ContactPay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_pay);

        Contact c = (Contact) getIntent().getSerializableExtra("Contact");

        TextView nombre = (TextView) findViewById(R.id.cpay_nombre);
        nombre.setText(c.getNombre() + " " + c.getApellido());

        TextView numero = (TextView) findViewById(R.id.cpay_numero);
        numero.setText(c.getTelefono());

        Spinner categories = (Spinner) findViewById(R.id.categories_spinner);
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
    }
}
