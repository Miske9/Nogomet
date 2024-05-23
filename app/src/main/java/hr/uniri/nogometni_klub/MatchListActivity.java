package hr.uniri.nogometni_klub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hr.uniri.nogometni_klub.AppDatabase;
import hr.uniri.nogometni_klub.R;

public class MatchListActivity extends AppCompatActivity {

    EditText domaci_klub, gost_klub, rezultat;
    Button btnSpremiMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        domaci_klub = findViewById(R.id.editDomaciKlub);
        gost_klub = findViewById(R.id.editGostKlub);
        rezultat = findViewById(R.id.editRezultat);
        btnSpremiMatch = findViewById(R.id.btnSpremiMatch);
        btnSpremiMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase appDatabase = new AppDatabase(MatchListActivity.this);
                appDatabase.addMatch(
                        domaci_klub.getText().toString().trim(),
                        gost_klub.getText().toString().trim(),
                        Integer.parseInt(rezultat.getText().toString().trim())
                );
            }
        });

    }
}