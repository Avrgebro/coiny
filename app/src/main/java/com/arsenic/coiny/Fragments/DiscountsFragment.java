package com.arsenic.coiny.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arsenic.coiny.Activities.DiscountInfo;
import com.arsenic.coiny.Adapters.DiscountAdapter;
import com.arsenic.coiny.Interfaces.OnItemClickListener;
import com.arsenic.coiny.Model.Discount;
import com.arsenic.coiny.R;

import java.util.ArrayList;
import java.util.List;


public class DiscountsFragment extends Fragment{

    private static final String TAG = "Discounts Fragment";
    View view;
    List<Discount> discounts;

    public DiscountsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_discounts, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Discount d1 = new Discount("Cineplanet", "2x1 en entradas de estrenos", "", R.drawable.cineplanet);
        Discount d2 = new Discount("PinkBerry", "4 toppings gratis para cualquier helado", "", R.drawable.cineplanet);

        discounts= new ArrayList<>();
        discounts.add(d1);
        discounts.add(d2);

        RecyclerView discount_list = view.findViewById(R.id.discounts_list);
        discount_list.setLayoutManager(new LinearLayoutManager(getContext()));

        discount_list.setAdapter(new DiscountAdapter(getActivity(), discounts, new OnItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                Discount disc = (Discount)item;
                //Toast.makeText(getActivity(), ((Discount) item).getCompany(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), DiscountInfo.class);
                startActivity(intent);
            }
        }));

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
