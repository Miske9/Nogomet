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
import java.util.List;

public class MatchesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private MatchAdapter matchAdapter;
    private FirebaseFirestore db;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

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

        recyclerView = findViewById(R.id.recyclerViewMatches);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        fetchMatches();

        Button dodajUtakmice = findViewById(R.id.btnDodajUtakmice);
        dodajUtakmice.setOnClickListener(v -> startActivity(new Intent(MatchesActivity.this, AddMatchActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchMatches();
    }

    private void fetchMatches() {
        db.collection("matches")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Match> matches = task.getResult().toObjects(Match.class);
                        matchAdapter = new MatchAdapter(matches, match -> {
                            Intent intent = new Intent(MatchesActivity.this, UpdateMatchActivity.class);
                            intent.putExtra("matchId", match.getId());
                            startActivity(intent);
                        });
                        recyclerView.setAdapter(matchAdapter);

                    } else {
                        Toast.makeText(MatchesActivity.this, "Error kod dohvaćanja utakmica", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(MatchesActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_players) {
            Intent intent = new Intent(MatchesActivity.this, PlayersActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_matches) {
            Intent intent = new Intent(MatchesActivity.this, MatchesActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_table) {
            Intent intent = new Intent(MatchesActivity.this, TableActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_about) {
            Intent intent = new Intent(MatchesActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
