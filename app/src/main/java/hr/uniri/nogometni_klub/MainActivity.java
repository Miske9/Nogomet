package hr.uniri.nogometni_klub;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // Pronalaženje gumba za igrače
        Button btnIgraci = findViewById(R.id.btnIgraci);
        btnIgraci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pokretanje PlayerActivity-a kada se pritisne gumb za igrače
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                startActivity(intent);
            }
        });

        // Pronalaženje gumba za utakmice
        Button btnUtakmice = findViewById(R.id.btnUtakmice);
        btnUtakmice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pokretanje MatchesActivity-a kada se pritisne gumb za utakmice
                Intent intent = new Intent(MainActivity.this, MatchesActivity.class);
                startActivity(intent);
            }
        });

        // Pronalaženje gumba za tablicu
        Button btnTablica = findViewById(R.id.btnTablica);
        btnTablica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pokretanje StandingsActivity-a kada se pritisne gumb za tablicu
                Intent intent = new Intent(MainActivity.this, StandingsActivity.class);
                startActivity(intent);
            }
        });

        // Pronalaženje gumba za "O nama"
        Button btnAbout = findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pokretanje AboutActivity-a kada se pritisne gumb za "O nama"
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
