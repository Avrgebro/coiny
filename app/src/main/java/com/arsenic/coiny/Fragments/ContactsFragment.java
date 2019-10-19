package com.arsenic.coiny.Fragments;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arsenic.coiny.Activities.Authentication.Login;
import com.arsenic.coiny.Activities.ContactPay;

import com.arsenic.coiny.Adapters.ContactAdapter;

import com.arsenic.coiny.Interfaces.OnItemClickListener;
import com.arsenic.coiny.MainActivity;
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





        /*final ProgressDialog dialog = new ProgressDialog(getActivity());

        dialog.setMessage("Obteniendo contactos!!");
        dialog.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

            }
        }, 2000);*/

        //List<Contact> contacts= getAllContacts();
        RecyclerView contact_list = view.findViewById(R.id.contacts_list);
        contact_list.setLayoutManager(new LinearLayoutManager(getContext()));

        contact_list.setAdapter(new ContactAdapter(getActivity(), contacts, new OnItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                Contact cont = (Contact)item;

                Intent intent = new Intent(getActivity(), ContactPay.class);
                intent.putExtra("Contact", cont);
                startActivity(intent);
            }
        }));
    }

    private ArrayList getAllContacts() {
        ArrayList<Contact> nameList = new ArrayList<>();
        ContentResolver cr = getActivity().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);




                if(phones.moveToNext()){
                    String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));

                    if(type== ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE){
                        Contact c = new Contact(name, "   ", number);
                        nameList.add(c);
                    }


                }

                if (cur.getInt(cur.getColumnIndex( ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    pCur.close();
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
        return nameList;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
