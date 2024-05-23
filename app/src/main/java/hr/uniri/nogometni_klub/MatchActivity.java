package hr.uniri.nogometni_klub;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MatchActivity extends AppCompatActivity {

    RecyclerView match_recyclerView;
    FloatingActionButton add_match_button;
    ImageView match_empty_imageview;
    TextView match_no_data;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    AppDatabase appDatabase;
    ArrayList<String> match_ID, domaci_klub, gost_klub, rezultat;
    MatchAdapter matchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Toolbar toolbar = findViewById(R.id.toolbar_match);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_match);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_match);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    Intent intent = new Intent(MatchActivity.this, MainActivity.class);
                    startActivity(intent);
                    // Handle other navigation items if needed
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        match_recyclerView = findViewById(R.id.match_recyclerView);
        add_match_button = findViewById(R.id.add_match_button);
        match_empty_imageview = findViewById(R.id.match_empty_imageview);
        match_no_data = findViewById(R.id.match_no_data);
        add_match_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MatchActivity.this, MatchListActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        appDatabase = new AppDatabase(MatchActivity.this);
        match_ID = new ArrayList<>();
        domaci_klub = new ArrayList<>();
        gost_klub = new ArrayList<>();
        rezultat = new ArrayList<>();

        storeDataInArrays();

        matchAdapter = new MatchAdapter(MatchActivity.this, this, match_ID, domaci_klub, gost_klub, rezultat);
        match_recyclerView.setAdapter(matchAdapter);
        match_recyclerView.setLayoutManager(new LinearLayoutManager(MatchActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = appDatabase.readAllMatchData();
        if (cursor.getCount() == 0) {
            match_empty_imageview.setVisibility(View.VISIBLE);
            match_no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                match_ID.add(cursor.getString(0));
                domaci_klub.add(cursor.getString(1));
                gost_klub.add(cursor.getString(2));
                rezultat.add(cursor.getString(3));
            }
            match_empty_imageview.setVisibility(View.GONE);
            match_no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
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
