package com.arsenic.coiny;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.arsenic.coiny.Fragments.ContactsFragment;
import com.arsenic.coiny.Fragments.DiscountsFragment;
import com.arsenic.coiny.Fragments.MoreFragment;
import com.arsenic.coiny.Fragments.StartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity: ";

    final Fragment startFragment = new StartFragment();
    final Fragment discountsFragment = new DiscountsFragment();
    final Fragment contactsFragment = new ContactsFragment();
    final Fragment moreFragment = new MoreFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = startFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.menu_start:
                    fm.beginTransaction().hide(active).show(startFragment).commit();
                    active = startFragment;
                    return true;

                case R.id.menu_discounts:
                    fm.beginTransaction().hide(active).show(discountsFragment).commit();
                    active = discountsFragment;
                    return true;

                case R.id.menu_contacts:
                    fm.beginTransaction().hide(active).show(contactsFragment).commit();
                    active = contactsFragment;
                    return true;

                case R.id.menu_more:
                    fm.beginTransaction().hide(active).show(moreFragment).commit();
                    active = moreFragment;
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        navigation.invalidate();

        fm.beginTransaction().add(R.id.mainfragment, moreFragment, "4").hide(moreFragment).commit();
        fm.beginTransaction().add(R.id.mainfragment, contactsFragment, "3").hide(contactsFragment).commit();
        fm.beginTransaction().add(R.id.mainfragment, discountsFragment, "2").hide(discountsFragment).commit();
        fm.beginTransaction().add(R.id.mainfragment, startFragment, "1").commit();



    }
}
