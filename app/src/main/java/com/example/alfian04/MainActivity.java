package com.example.alfian04;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private NavigationView navigationView;

    private final String[] tabTitles = new String[]{
            "Beranda",
            "Fitur Data",
            "Kalkulator",
            "Dokumen",
            "Peta Lokasi"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Inisialisasi Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 2. Inisialisasi DrawerLayout & NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // 3. Pasang Hamburger Menu (ActionBarDrawerToggle)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.menu_home,
                R.string.menu_exit
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 4. Inisialisasi ViewPager2 & PageAdapter (5 Halaman)
        viewPager = findViewById(R.id.view_pager);
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        // 5. Inisialisasi TabLayout & Integrasikan dengan ViewPager2
        tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position < tabTitles.length) {
                tab.setText(tabTitles[position]);
            }
        }).attach();

        // Sinkronkan pilihan item pada Navigation Drawer saat user swipe halaman
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        navigationView.setCheckedItem(R.id.nav_home);
                        break;
                    case 1:
                        navigationView.setCheckedItem(R.id.nav_feature);
                        break;
                    case 2:
                        navigationView.setCheckedItem(R.id.nav_calculator);
                        break;
                    case 3:
                        navigationView.setCheckedItem(R.id.nav_document);
                        break;
                    case 4:
                        navigationView.setCheckedItem(R.id.nav_maps);
                        break;
                }
            }
        });
    }

    // 6. Options Menu (Ikon Keluar di toolbar & Titik Tiga berisi Tentang)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            showAboutDialog();
            return true;
        } else if (id == R.id.action_exit_toolbar) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 7. Navigation Drawer Menu Item Selected Handler
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            viewPager.setCurrentItem(0, true);
        } else if (id == R.id.nav_feature) {
            viewPager.setCurrentItem(1, true);
        } else if (id == R.id.nav_calculator) {
            viewPager.setCurrentItem(2, true);
        } else if (id == R.id.nav_document) {
            viewPager.setCurrentItem(3, true);
        } else if (id == R.id.nav_maps) {
            viewPager.setCurrentItem(4, true);
        } else if (id == R.id.nav_exit) {
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // Navigasi Back Button: Tutup drawer jika sedang terbuka
    @Override
    public void onBackPressed() {
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Tentang Alfian04")
                .setMessage("Aplikasi Android Alfian04 oleh Alfian Dafari (04)\n\nFitur Navigasi Lengkap:\n- Navigation Drawer & Hamburger Menu\n- Options Menu & Ikon Keluar Toolbar\n- TabLayout & ViewPager2\n- 5 Fragment Utama:\n  1. Beranda\n  2. Form Penilaian Siswa\n  3. Kalkulator Cepat\n  4. Panduan & Informasi\n  5. Peta Lokasi Google Maps Dinamis\n\n100% Java & XML Layout • Tema Indigo–Violet.")
                .setPositiveButton("TUTUP", (dialog, which) -> dialog.dismiss())
                .show();
    }
}