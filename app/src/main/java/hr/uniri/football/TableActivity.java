package hr.uniri.football;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;
import java.util.List;

public class TableActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private TableAdapter tableAdapter;
    private FirebaseFirestore db;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Smoljanci Sloboda");

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView = findViewById(R.id.recyclerViewTable);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        fetchTables();

        Button dodajKlub = findViewById(R.id.btnDodajKlub);
        dodajKlub.setOnClickListener(v -> startActivity(new Intent(TableActivity.this, AddTableActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchTables();
    }
    private void fetchTables() {
        db.collection("tables")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Table> tables = task.getResult().toObjects(Table.class);
                        // Sort tables by points descending
                        Collections.sort(tables, (t1, t2) -> t2.getPoints() - t1.getPoints());

                        tableAdapter = new TableAdapter(tables, table -> {
                            Intent intent = new Intent(TableActivity.this, UpdateTableActivity.class);
                            intent.putExtra("tableId", table.getId());
                            startActivity(intent);
                        });

                        recyclerView.setAdapter(tableAdapter); // Postavljanje adaptera na RecyclerView
                    } else {
                        Toast.makeText(TableActivity.this, "Error getting tables: " + task.getException(), Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            Intent intent = new Intent(TableActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_players) {
            Intent intent = new Intent(TableActivity.this, PlayersActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_matches) {
            Intent intent = new Intent(TableActivity.this, MatchesActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_table) {
            Intent intent = new Intent(TableActivity.this, TableActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_about) {
            Intent intent = new Intent(TableActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}


