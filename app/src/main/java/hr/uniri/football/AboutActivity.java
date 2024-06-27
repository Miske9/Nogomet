package hr.uniri.football;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class AboutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private EditText textViewTitle, textViewLocation, textViewMembers, textViewLeague;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Smoljanci Sloboda");

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open_nav, R.string.close_nav);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Pronalaženje TextView elemenata iz layout-a
        TextView textViewTitle = findViewById(R.id.textViewTitle);
        TextView textViewLocation = findViewById(R.id.textViewLocation);
        TextView textViewMembers = findViewById(R.id.textViewMembers);
        TextView textViewLeague = findViewById(R.id.textViewLeague);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            Intent intent = new Intent(AboutActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_players) {
            Intent intent = new Intent(AboutActivity.this, PlayersActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_matches) {
            Intent intent = new Intent(AboutActivity.this, MatchesActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_table) {
            Intent intent = new Intent(AboutActivity.this, TableActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.nav_about) {
            Intent intent = new Intent(AboutActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
