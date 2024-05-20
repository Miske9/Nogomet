package hr.uniri.nogometni_klub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hr.uniri.nogometni_klub.AppDatabase;
import hr.uniri.nogometni_klub.R;

public class PlayerListActivity extends AppCompatActivity {

    EditText editIme, editPrezime, editGodine, editPozicija;
    Button btnSpremi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        editIme = findViewById(R.id.editIme);
        editPrezime = findViewById(R.id.editPrezime);
        editGodine = findViewById(R.id.editGodine);
        editPozicija = findViewById(R.id.editPozicija);
        btnSpremi = findViewById(R.id.btnSpremi);
        btnSpremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase appDatabase = new AppDatabase(PlayerListActivity.this);
                appDatabase.addPlayer(
                        editIme.getText().toString().trim(),
                        editPrezime.getText().toString().trim(),
                        Integer.parseInt(editGodine.getText().toString().trim()),
                        editPozicija.getText().toString().trim()
                );
            }
        });

    }
}