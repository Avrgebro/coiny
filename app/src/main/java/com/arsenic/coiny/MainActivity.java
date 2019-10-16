package com.arsenic.coiny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.arsenic.coiny.DBController.DBManager;
import com.arsenic.coiny.Fragments.ContactsFragment;
import com.arsenic.coiny.Fragments.DiscountsFragment;
import com.arsenic.coiny.Fragments.MoreFragment;
import com.arsenic.coiny.Fragments.StartFragment;
import com.arsenic.coiny.Model.Usuario;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity: ";

    final Fragment startFragment = new StartFragment();
    final Fragment discountsFragment = new DiscountsFragment();
    final Fragment contactsFragment = new ContactsFragment();
    final Fragment moreFragment = new MoreFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = startFragment;
    LinearLayout appbar;
    DBManager db;
    Usuario luser;

    public BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.menu_start:
                    fm.beginTransaction().hide(active).show(startFragment).commit();
                    active = startFragment;
                    appbar.setVisibility(View.GONE);
                    return true;

                case R.id.menu_discounts:
                    fm.beginTransaction().hide(active).show(discountsFragment).commit();
                    active = discountsFragment;
                    appbar.setVisibility(View.VISIBLE);
                    return true;

                case R.id.menu_contacts:
                    fm.beginTransaction().hide(active).show(contactsFragment).commit();
                    active = contactsFragment;
                    appbar.setVisibility(View.VISIBLE);
                    return true;

                case R.id.menu_more:
                    fm.beginTransaction().hide(active).show(moreFragment).commit();
                    active = moreFragment;
                    appbar.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        appbar = (LinearLayout) findViewById(R.id.appbar);
        appbar.setVisibility(View.GONE);

        db = new DBManager(this);

        navigation.invalidate();

        fm.beginTransaction().add(R.id.mainfragment, moreFragment, "4").hide(moreFragment).commit();
        fm.beginTransaction().add(R.id.mainfragment, contactsFragment, "3").hide(contactsFragment).commit();
        fm.beginTransaction().add(R.id.mainfragment, discountsFragment, "2").hide(discountsFragment).commit();
        fm.beginTransaction().add(R.id.mainfragment, startFragment, "1").commit();

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String number = sp.getString("number", null);

        luser = db.getUsuario(number);





    }
}
