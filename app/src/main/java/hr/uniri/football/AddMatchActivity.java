package hr.uniri.football;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddMatchActivity extends AppCompatActivity {
    private EditText editTextHomeTeam, editTextAwayTeam, editTextHomeScore, editTextAwayScore;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);

        editTextHomeTeam = findViewById(R.id.editTextHomeTeam);
        editTextAwayTeam = findViewById(R.id.editTextAwayTeam);
        editTextHomeScore = findViewById(R.id.editTextHomeScore);
        editTextAwayScore = findViewById(R.id.editTextAwayScore);
        Button buttonAddMatch = findViewById(R.id.buttonAddMatch);

        db = FirebaseFirestore.getInstance();

        buttonAddMatch.setOnClickListener(v -> {
            String homeTeam = editTextHomeTeam.getText().toString().trim();
            String awayTeam = editTextAwayTeam.getText().toString().trim();
            String homeScore = editTextHomeScore.getText().toString().trim();
            String awayScore = editTextAwayScore.getText().toString().trim();

            if (homeTeam.isEmpty() || awayTeam.isEmpty() || homeScore.isEmpty() || awayScore.isEmpty()) {
                Toast.makeText(AddMatchActivity.this, "Molimo ispunite sva polja.", Toast.LENGTH_SHORT).show();
                return;
            }

            addMatch(homeTeam, awayTeam, homeScore, awayScore);
        });
    }

    private void addMatch(String homeTeam, String awayTeam, String homeScore, String awayScore) {
        String id = db.collection("matches").document().getId();
        Match match = new Match(id, homeTeam, awayTeam, homeScore, awayScore);

        db.collection("matches").document(id).set(match)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(AddMatchActivity.this, "Utakmica dodana", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(AddMatchActivity.this, "Error kod dodavanja utakmice.", Toast.LENGTH_SHORT).show());
    }
}
