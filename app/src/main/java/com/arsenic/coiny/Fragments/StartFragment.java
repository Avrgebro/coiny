package com.arsenic.coiny.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arsenic.coiny.Activities.Budget;
import com.arsenic.coiny.Activities.ContactPay;
import com.arsenic.coiny.Activities.Exchange;
import com.arsenic.coiny.Activities.Savings;
import com.arsenic.coiny.Activities.Services;
import com.arsenic.coiny.R;


public class StartFragment extends Fragment {

    private static final String TAG = "Start Fragment";
    View view;


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


            }
        });


    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
