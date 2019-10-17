package com.arsenic.coiny.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arsenic.coiny.Activities.Budget;
import com.arsenic.coiny.Activities.ContactPay;
import com.arsenic.coiny.Activities.Exchange;
import com.arsenic.coiny.Activities.Savings;
import com.arsenic.coiny.Activities.Services;
import com.arsenic.coiny.DBController.DBManager;
import com.arsenic.coiny.MainActivity;
import com.arsenic.coiny.Model.Usuario;
import com.arsenic.coiny.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;

import static android.content.Context.MODE_PRIVATE;


public class StartFragment extends Fragment {

    private static final String TAG = "Start Fragment";
    View view;
    DBManager db;
    Usuario u;


    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_start, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout btn_services = (LinearLayout) getActivity().findViewById(R.id.button4);
        LinearLayout btn_budget = (LinearLayout) getActivity().findViewById(R.id.button5);
        LinearLayout btn_exchange = (LinearLayout) getActivity().findViewById(R.id.button7);
        LinearLayout btn_savings = (LinearLayout) getActivity().findViewById(R.id.button1);
        LinearLayout btn_payment = (LinearLayout) getActivity().findViewById(R.id.button3);

        btn_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Services.class);
                startActivity(intent);
            }
        });

        btn_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Budget.class);
                startActivity(intent);
            }
        });

        btn_exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Exchange.class);
                startActivity(intent);
            }
        });

        btn_savings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Savings.class);
                startActivity(intent);
            }
        });

        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationView n = ((MainActivity)getActivity()).navigation;
                n.setSelectedItemId(R.id.menu_contacts);

            }
        });

        SharedPreferences sp = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        String number = sp.getString("number", null);

        db = new DBManager(getActivity());

        u = db.getUsuario(number);

        TextView hello = (TextView) view.findViewById(R.id.start_hello);
        hello.setText("Hola " + u.getNombre());

        LinearLayout showsaldo = view.findViewById(R.id.show_saldo);

        showsaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = db.getUsuario(u.getNumero());

                DecimalFormat df = new DecimalFormat("#.##");


                /*AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setMessage("S/. " + df.format(u.getSaldo_sol()) + "\nUS$ " + df.format(u.getSaldo_dol()));
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                TextView textView = (TextView) alert11.findViewById(android.R.id.message);
                textView.setTextSize(25);
                textView.setGravity(Gravity.CENTER);*/

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                ViewGroup viewGroup = v.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_view, viewGroup, false);
                TextView dsol = (TextView) dialogView.findViewById(R.id.dialog_soles);
                TextView ddol = (TextView) dialogView.findViewById(R.id.dialog_dolares);

                dsol.setText("S/ " + df.format(u.getSaldo_sol()));
                ddol.setText("US$ " + df.format(u.getSaldo_dol()));
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });



    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
