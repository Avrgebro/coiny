package com.arsenic.coiny.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arsenic.coiny.Activities.Authentication.Login;
import com.arsenic.coiny.Activities.More.Help;
import com.arsenic.coiny.Activities.More.Transactions;
import com.arsenic.coiny.DBController.DBManager;
import com.arsenic.coiny.MainActivity;
import com.arsenic.coiny.Model.Usuario;
import com.arsenic.coiny.R;
import com.google.android.material.textfield.TextInputEditText;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MoreFragment extends Fragment {

    private static final String TAG = "More Fragment";
    View view;
    DBManager db;
    Usuario u;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_more, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sp = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        String number = sp.getString("number", null);

        db = new DBManager(getActivity());

        u = db.getUsuario(number);

        TextView name = (TextView) view.findViewById(R.id.more_name);
        name.setText(u.getNombre() + " " + u.getApellido());

        TextView num = (TextView) view.findViewById(R.id.more_number);
        num.setText(u.getNumero());

        TextView logout = (TextView) view.findViewById(R.id.more_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getActivity().getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                editor.putInt("user_logged", 0);
                editor.putString("number", null);

                editor.apply();

                Intent intent = new Intent(getActivity(),
                        Login.class);
                startActivity(intent);

                getActivity().finish();
            }
        });

        RelativeLayout help = (RelativeLayout) view.findViewById(R.id.more_ayuda);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Help.class);
                startActivity(intent);
            }
        });

        RelativeLayout moves = (RelativeLayout) view.findViewById(R.id.more_move);
        moves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Transactions.class);
                startActivity(intent);
            }
        });

        RelativeLayout ref = (RelativeLayout) view.findViewById(R.id.more_referir);
        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                ViewGroup viewGroup = v.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_reffer, viewGroup, false);
                Button rfr = (Button) dialogView.findViewById(R.id.reffer);


                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                rfr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Recibiras S/5 cuando tu contacto se registre!!", Toast.LENGTH_LONG).show();
                        alertDialog.dismiss();
                    }
                });
            }
        });


    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
