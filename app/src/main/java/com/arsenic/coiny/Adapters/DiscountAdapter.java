package com.arsenic.coiny.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arsenic.coiny.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder>{

    private List discounts;

    public DiscountAdapter(List discounts){
        this.discounts = discounts;
    }

    @NonNull
    @Override
    public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.discount_list_item, parent, false);
        return new DiscountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DiscountViewHolder extends RecyclerView.ViewHolder {

        CircleImageView company_logo;
        TextView company_name;
        TextView discount_desc;

        public DiscountViewHolder(@NonNull View itemView) {
            super(itemView);

            company_logo = itemView.findViewById(R.id.discount_list_logo);
            company_name = itemView.findViewById(R.id.discount_list_company);
            discount_desc = itemView.findViewById(R.id.discount_list_desc);
        }
    }

}


