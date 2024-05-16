/*package hr.uniri.nogometni_klub;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import hr.uniri.nogometni_klub.AppDatabase;
import hr.uniri.nogometni_klub.Player;
import hr.uniri.nogometni_klub.R;

public class PlayerListActivity extends AppCompatActivity {

    private ListView listViewPlayers;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        listViewPlayers = findViewById(R.id.listViewPlayers);

        // Inicijalizacija baze podataka
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database")
                .allowMainThreadQueries() // Ovo je privremeno, koristite AsyncTask ili druge metode za rad na pozadini
                .build();

        // Dohvat igrača iz baze podataka
        List<Player> players = appDatabase.playerDao().getAllPlayers();

        // Prikaz igrača u ListView-u
        List<String> playerNames = new ArrayList<>();
        for (Player player : players) {
            playerNames.add(player.getIme() + " " + player.getPrezime());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playerNames);
        listViewPlayers.setAdapter(adapter);
    }
}*/
