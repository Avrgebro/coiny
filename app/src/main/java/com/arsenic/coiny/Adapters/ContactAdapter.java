package com.arsenic.coiny.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arsenic.coiny.Model.Contact;
import com.arsenic.coiny.Model.Discount;
import com.arsenic.coiny.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;
    private Context context;

    public ContactAdapter(Context context, List contacts){
        this.contacts = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.contact_list_item, parent, false);
        return new ContactAdapter.ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        holder.initials.setText(contact.getInitials());
        String showname = contact.getNombre() + " " + contact.getApellido();
        holder.name.setText(showname);
        holder.number.setText(contact.getTelefono());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView initials;
        TextView name;
        TextView number;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            initials = itemView.findViewById(R.id.contact_list_initials);
            name = itemView.findViewById(R.id.contact_list_name);
            number = itemView.findViewById(R.id.contact_list_number);
        }
    }
}
