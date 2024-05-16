package hr.uniri.nogometni_klub;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class PlayerActivity extends AppCompatActivity {

    private EditText editIme, editPrezime, editGodine, editPozicija;

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        editIme = findViewById(R.id.editIme);
        editPrezime = findViewById(R.id.editPrezime);
        editGodine = findViewById(R.id.editGodine);
        editPozicija = findViewById(R.id.editPozicija);

        Button btnSpremi = findViewById(R.id.btnSpremi);

        // Inicijalizacija baze podataka
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database")
                .allowMainThreadQueries() // Ovo je privremeno, koristite AsyncTask ili druge metode za rad na pozadini
                .build();

        btnSpremi.setOnClickListener(v -> {
            String ime = editIme.getText().toString();
            String prezime = editPrezime.getText().toString();
            String godineStr = editGodine.getText().toString();
            String pozicija = editPozicija.getText().toString();

            if (ime.isEmpty() || prezime.isEmpty() || godineStr.isEmpty() || pozicija.isEmpty()) {
                Toast.makeText(PlayerActivity.this, "Molimo vas da popunite sva polja", Toast.LENGTH_SHORT).show();
                return;
            }

            int godine = Integer.parseInt(godineStr);

            // Stvaranje novog igrača
            Player player = new Player(ime, prezime, godine, pozicija);

            // Spremanje igrača u bazu podataka
            appDatabase.playerDao().insert(player);

            Toast.makeText(PlayerActivity.this, "Igrač spremljen u bazu podataka", Toast.LENGTH_SHORT).show();

            // Nakon spremanja možete očistiti unesene podatke iz EditText polja
            editIme.setText("");
            editPrezime.setText("");
            editGodine.setText("");
            editPozicija.setText("");
        });
    }
}


