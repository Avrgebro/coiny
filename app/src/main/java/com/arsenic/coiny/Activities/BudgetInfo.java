package com.arsenic.coiny.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arsenic.coiny.DBController.DBManager;
import com.arsenic.coiny.Model.Budget;
import com.arsenic.coiny.Model.Usuario;
import com.arsenic.coiny.R;
import com.google.android.material.textfield.TextInputEditText;
import com.mancj.slimchart.SlimChart;

public class BudgetInfo extends AppCompatActivity {

    Usuario u;
    DBManager db;
    TextInputEditText nbv;
    Budget b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_info);

        db = new DBManager(this);

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String number = sp.getString("number", null);

        u = db.getUsuario(number);

        b = (Budget) getIntent().getSerializableExtra("Budget");

        ImageView upnav = (ImageView) findViewById(R.id.binfo_upnav);
        upnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView totals = (TextView) findViewById(R.id.totalsbudget);
        totals.setText("S/ " + b.getUsed() + " / S/ " + b.getBudget() );


        SlimChart slimChart = (SlimChart) findViewById(R.id.slimChart);

        double porc = (b.getUsed()*100)/b.getBudget();
        Log.i("Ptc ",porc+"" );

        if(db.getBudget(u.getNumero(), b.getType()) > 0.0){
            porc = db.getUsedBudget(u.getNumero(), b.getType()) / db.getBudget(u.getNumero(), b.getType());
        }

        int ptc = (int) porc;
        Log.i("Ptc ",ptc+"" );

        //Optional - create colors array
        int[] colors = new int[4];
        colors[0] = R.color.colorPrimary;
        colors[1] = R.color.colorAccent;
        //slimChart.setColors(colors);

        final float[] stats = new float[4];
        stats[0] = 100;
        stats[1] = ptc;

        slimChart.setStats(stats);



        slimChart.setText(ptc + "%");

        slimChart.setColor(ContextCompat.getColor(this, R.color.colorPrimary));

        //Play animation
        slimChart.setStartAnimationDuration(2000);

        nbv = (TextInputEditText) findViewById(R.id.newb_value);

        Button bbtn = (Button) findViewById(R.id.budget_btn);
        bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double val = Double.parseDouble(nbv.getText().toString());

                if(val >= 0.0){

                    db.updateBudget(u.getNumero(), b.getType(), val);

                    final ProgressDialog dialog = new ProgressDialog(BudgetInfo.this);

                    dialog.setMessage("Actualizando tu presupuesto!!");
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
                    Toast.makeText(BudgetInfo.this, "No seas gil pe mano", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

