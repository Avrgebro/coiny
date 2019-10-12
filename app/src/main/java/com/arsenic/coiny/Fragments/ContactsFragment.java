package com.arsenic.coiny.Fragments;

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

import com.arsenic.coiny.Adapters.ContactAdapter;
import com.arsenic.coiny.Adapters.DiscountAdapter;
import com.arsenic.coiny.Model.Contact;
import com.arsenic.coiny.R;

import java.util.ArrayList;
import java.util.List;

/*
* Currently playing:
* Del Recuerdo
* Sharif ft. MXRGXN
* */


public class ContactsFragment extends Fragment {

    private static final String TAG = "Contacts Fragment";
    View view;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contacts, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Contact c1 = new Contact("Alvarito", "Calvo", "920014330");
        Contact c2 = new Contact("Gustavo", "Levano", "950015593");
        Contact c3 = new Contact("Jose", "Bejarano", "981739024");

        List<Contact> contacts= new ArrayList<>();
        contacts.add(c1);
        contacts.add(c2);
        contacts.add(c3);

        RecyclerView contact_list = view.findViewById(R.id.contacts_list);
        contact_list.setLayoutManager(new LinearLayoutManager(getContext()));

        contact_list.setAdapter(new ContactAdapter(getActivity(), contacts));
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
