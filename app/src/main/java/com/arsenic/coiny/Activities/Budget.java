package com.arsenic.coiny.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arsenic.coiny.DBController.DBManager;
import com.arsenic.coiny.R;

public class Budget extends AppCompatActivity {

    TextView shoptv;
    TextView trantv;
    TextView famitv;
    TextView entrtv;
    TextView foodtv;

    DBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        db = new DBManager(this);

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String number = sp.getString("number", null);

        double bshop = db.getBudget(number, "shopping");
        double btran = db.getBudget(number, "transporte");
        double bfami = db.getBudget(number, "familia");
        double bentr = db.getBudget(number, "entretenimiento");
        double bfood = db.getBudget(number, "familia");

        double ubshop = db.getUsedBudget(number, "shopping");
        double ubtran = db.getUsedBudget(number, "transporte");
        double ubfami = db.getUsedBudget(number, "familia");
        double ubentr = db.getUsedBudget(number, "entretenimiento");
        double ubfood = db.getUsedBudget(number, "familia");


        ImageView upnav = (ImageView) findViewById(R.id.budget_upnav);
        upnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RelativeLayout shop = (RelativeLayout) findViewById(R.id.b_shopping);
        RelativeLayout tran = (RelativeLayout) findViewById(R.id.b_transporte);
        RelativeLayout fami = (RelativeLayout) findViewById(R.id.b_familia);
        RelativeLayout entr = (RelativeLayout) findViewById(R.id.b_entretenimiento);
        RelativeLayout food = (RelativeLayout) findViewById(R.id.b_comida);

        shoptv = (TextView) findViewById(R.id.shop_amt);
        famitv = (TextView) findViewById(R.id.fam_amt);
        trantv = (TextView) findViewById(R.id.tran_amt);
        entrtv = (TextView) findViewById(R.id.ent_amt);
        foodtv = (TextView) findViewById(R.id.com_amt);

        shoptv.setText(ubshop + "/" + bshop);
        famitv.setText(ubfami + "/" + bfami);
        trantv.setText(ubtran + "/" + btran);
        entrtv.setText(ubentr + "/" + bentr);
        foodtv.setText(ubfood + "/" + bfood);



        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick("shopping",shoptv);
            }
        });

        tran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick("transporte",trantv);
            }
        });

        fami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick("familia",famitv);
            }
        });

        entr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick("entretenimiento",entrtv);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick("comida",foodtv);
            }
        });


    }

    public void handleClick(String type, TextView amount){

        Toast.makeText(this, "GAAAAA" + type, Toast.LENGTH_SHORT).show();
    }
}
