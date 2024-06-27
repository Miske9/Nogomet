package hr.uniri.football;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateMatchActivity extends AppCompatActivity {
    private EditText editTextTeam1, editTextTeam2, editTextHomeScore, editTextAwayScore;
    private FirebaseFirestore db;
    private String matchId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_match);

        editTextTeam1 = findViewById(R.id.editTextTeam1);
        editTextTeam2 = findViewById(R.id.editTextTeam2);
        editTextHomeScore = findViewById(R.id.editTextHomeScore);
        editTextAwayScore = findViewById(R.id.editTextAwayScore);
        Button UpdateMatch = findViewById(R.id.btnUpdateMatch);
        Button DeleteMatch = findViewById(R.id.btnDeleteMatch);

        db = FirebaseFirestore.getInstance();
        matchId = getIntent().getStringExtra("matchId");

        loadMatchData();

        UpdateMatch.setOnClickListener(v -> updateMatch());
        DeleteMatch.setOnClickListener(v -> deleteMatch());
    }

    private void loadMatchData() {
        db.collection("matches").document(matchId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Match match = documentSnapshot.toObject(Match.class);
                        if (match != null) {
                            editTextTeam1.setText(match.getHomeTeam());
                            editTextTeam2.setText(match.getAwayTeam());
                            editTextHomeScore.setText(match.getHomeScore());
                            editTextAwayScore.setText(match.getAwayScore());
                        }
                    }
                });
    }

    private void updateMatch() {
        String team1 = editTextTeam1.getText().toString();
        String team2 = editTextTeam2.getText().toString();
        String homeScore = editTextHomeScore.getText().toString();
        String awayScore = editTextAwayScore.getText().toString();

        db.collection("matches").document(matchId)
                .update("homeTeam", team1, "awayTeam", team2, "homeScore", homeScore, "awayScore", awayScore)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(UpdateMatchActivity.this, "Utakmica ažurirana", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(UpdateMatchActivity.this, "Error kod ažuriranja utakmice", Toast.LENGTH_SHORT).show());
    }

    private void deleteMatch() {
        db.collection("matches").document(matchId).delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(UpdateMatchActivity.this, "Utakmica obrisana", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(UpdateMatchActivity.this, "Error kod brisanja utakmice", Toast.LENGTH_SHORT).show());
    }
}
