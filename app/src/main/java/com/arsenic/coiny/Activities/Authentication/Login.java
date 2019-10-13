package com.arsenic.coiny.Activities.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.arsenic.coiny.DBController.DBManager;
import com.arsenic.coiny.MainActivity;
import com.arsenic.coiny.Model.Usuario;
import com.arsenic.coiny.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class Login extends AppCompatActivity {

    private TextInputEditText numberet;
    private TextInputEditText pinet;

    private TextInputLayout numbertil;
    private TextInputLayout pintil;

    private DBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBManager(this);

        Button login = (Button) findViewById(R.id.login);
        Button register = (Button) findViewById(R.id.register);

        numberet = (TextInputEditText) findViewById(R.id.numberet);
        pinet = (TextInputEditText) findViewById(R.id.pinet);

        numbertil = (TextInputLayout) findViewById(R.id.numbertil);
        pintil = (TextInputLayout) findViewById(R.id.pintil);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int n = 0;
                int p = 0;

                if(numberet.getText().toString().isEmpty()){
                    numbertil.setError("Ingresa tu número");

                }else{
                    Usuario user = db.getUsuario(numberet.getText().toString());

                    if(user == null){
                        Toast.makeText(Login.this, "Usuario no existe!!", Toast.LENGTH_SHORT).show();
                    }else{
                        n = 1;
                    }

                }

                if(pinet.getText().toString().isEmpty()){
                    pintil.setError("Ingresa tu pin");
                }else{
                    p = 1;
                }

                if(p == 1 && n == 1){
                    Intent intent = new Intent(getApplicationContext(),
                            MainActivity.class);
                    startActivity(intent);
                }


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}
