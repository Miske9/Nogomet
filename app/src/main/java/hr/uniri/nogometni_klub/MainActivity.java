package hr.uniri.nogometni_klub;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Button btnAbout = findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pokretanje MatchesActivity-a kada se pritisne gumb za utakmice
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }
}