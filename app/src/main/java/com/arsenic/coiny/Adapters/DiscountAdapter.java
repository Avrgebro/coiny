package com.arsenic.coiny.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arsenic.coiny.Model.Discount;
import com.arsenic.coiny.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder>{

    private List<Discount> discounts;
    private Context context;

    public DiscountAdapter(Context context, List discounts){
        this.discounts = discounts;
        this.context = context;
    }

    @NonNull
    @Override
    public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.discount_list_item, parent, false);
        return new DiscountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {
        Discount discount = discounts.get(position);

        holder.company_name.setText(discount.getCompany());
        holder.discount_desc.setText(discount.getSmall_description());
        holder.company_logo.setImageResource(discount.getImage_resource_id());

    }

    @Override
    public int getItemCount() {
        return discounts.size();
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


