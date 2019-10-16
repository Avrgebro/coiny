package com.arsenic.coiny.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arsenic.coiny.Interfaces.OnItemClickListener;
import com.arsenic.coiny.Model.Budget;
import com.arsenic.coiny.Model.Contact;
import com.arsenic.coiny.R;

import java.util.List;

/*
 * Created by: jose
 * Company: Dom Peru
 * Date: 10/15/19
 */

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.BudgetViewHolder> {

    private List<Budget> budgets;
    private Context context;
    private OnItemClickListener listener;

    public BudgetAdapter(Context context, List budgets, OnItemClickListener listener){
        this.budgets = budgets;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BudgetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.budget_list_item, parent, false);
        return new BudgetAdapter.BudgetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetViewHolder holder, int position) {
        Budget budget = budgets.get(position);

        holder.type.setText(budget.getType());
        String summary = budget.getUsed() + " / " + budget.getUsed();
        holder.summary.setText(summary);
        holder.image.setImageResource(budget.getResource_id());

        holder.bind(budget, listener);
    }

    @Override
    public int getItemCount() {
        return budgets.size();
    }

    public class BudgetViewHolder extends RecyclerView.ViewHolder {

        TextView type;
        TextView summary;
        ImageView image;

        public BudgetViewHolder(@NonNull View itemView) {
            super(itemView);

            type = itemView.findViewById(R.id.budget_list_name);
            summary = itemView.findViewById(R.id.budget_list_summary);
            image = itemView.findViewById(R.id.budget_image);
        }

        public void bind(final Budget item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
