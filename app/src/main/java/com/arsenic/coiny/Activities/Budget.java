package com.arsenic.coiny.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arsenic.coiny.Adapters.BudgetAdapter;
import com.arsenic.coiny.DBController.DBManager;
import com.arsenic.coiny.Interfaces.OnItemClickListener;
import com.arsenic.coiny.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Budget extends AppCompatActivity {

    DBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        db = new DBManager(this);

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String number = sp.getString("number", null);

        db.updateBudget(number, "shopping", 100.0);

        ImageView upnav = (ImageView) findViewById(R.id.budget_upnav);
        upnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        HashMap<String,Integer> ga = new HashMap<String,Integer>();

        ga.put("shopping", R.drawable.shopping);
        ga.put("transporte", R.drawable.transport);
        ga.put("comida", R.drawable.donut);
        ga.put("familia", R.drawable.family);
        ga.put("entretenimiento", R.drawable.theater);

        String[] types = {"shopping", "transporte", "comida", "familia", "entretenimiento"};

        List<com.arsenic.coiny.Model.Budget> budgets = new ArrayList<>();

        for(String t : types){
            com.arsenic.coiny.Model.Budget b = new com.arsenic.coiny.Model.Budget(t,db.getBudget(t,number),db.getUsedBudget(t,number),ga.get(t));
            budgets.add(b);
        }


        RecyclerView budget_list = (RecyclerView) findViewById(R.id.budget_list);
        budget_list.setLayoutManager(new LinearLayoutManager(this));

        budget_list.setAdapter(new BudgetAdapter(this, budgets, new OnItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                com.arsenic.coiny.Model.Budget bud = (com.arsenic.coiny.Model.Budget)item;

                Intent intent = new Intent(Budget.this, BudgetInfo.class);
                intent.putExtra("Budget", bud);
                startActivity(intent);
            }
        }));




    }

    public void handleClick(String type, TextView amount){

        Toast.makeText(this, "GAAAAA" + type, Toast.LENGTH_SHORT).show();
    }
}
