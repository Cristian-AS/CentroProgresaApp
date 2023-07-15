package com.uniminuto.appcentroprogresa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Perfil extends AppCompatActivity {

    final ProcessFragment processFragment = new ProcessFragment();
    final NotificationsFragment notificationsFragment = new NotificationsFragment();
    final ProfileFragment profileFragment = new ProfileFragment();
    final SettingsFragment settingsFragment = new SettingsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // lo primero que se carga
        loadFragment(profileFragment); 
    }
    // Para pasar de fragmentos
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        int itemId = item.getItemId();
        if (itemId == R.id.processFragment) {
            loadFragment(processFragment);
            return true;
        } else if (itemId == R.id.notificationsFragment) {
            loadFragment(notificationsFragment);
            return true;
        } else if (itemId == R.id.profileFragment) {
            loadFragment(profileFragment);
            return true;
        } else if (itemId == R.id.settingsFragment) {
            loadFragment(settingsFragment);
            return true;
        }
        return false;
    };

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}