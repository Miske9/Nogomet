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

public class PlayersActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private PlayerAdapter playerAdapter;
    private FirebaseFirestore db;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Smoljanci Sloboda");

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open_nav, R.string.close_nav);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView = findViewById(R.id.recyclerViewPlayers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        Button dodajIgraca = findViewById(R.id.btnDodajIgrace);
        dodajIgraca.setOnClickListener(v -> startActivity(new Intent(PlayersActivity.this, AddPlayerActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchPlayers();
    }

    private void fetchPlayers() {
        db.collection("players")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Player> players = task.getResult().toObjects(Player.class);
                        playerAdapter = new PlayerAdapter(players, player -> {
                            Intent intent = new Intent(PlayersActivity.this, UpdatePlayerActivity.class);
                            intent.putExtra("playerId", player.getId());
                            startActivity(intent);
                        });
                        recyclerView.setAdapter(playerAdapter);
                    } else {
                        Toast.makeText(PlayersActivity.this, "Error kod dohvaćanja igrača.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            Intent intent = new Intent(PlayersActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_players) {
            Intent intent = new Intent(PlayersActivity.this, PlayersActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_matches) {
            Intent intent = new Intent(PlayersActivity.this, MatchesActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_table) {
            Intent intent = new Intent(PlayersActivity.this, TableActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_about) {
            Intent intent = new Intent(PlayersActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
