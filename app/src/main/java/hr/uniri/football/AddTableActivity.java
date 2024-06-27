package hr.uniri.football;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddTableActivity extends AppCompatActivity {
    private EditText editTextName, editTextWins, editTextLosses, editTextDraws, editTextPoints;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_table);

        editTextName = findViewById(R.id.editTextName);
        editTextWins = findViewById(R.id.editTextWins);
        editTextLosses = findViewById(R.id.editTextLosses);
        editTextDraws = findViewById(R.id.editTextDraws);
        editTextPoints = findViewById(R.id.editTextPoints);
        Button buttonAddClub = findViewById(R.id.buttonAddClub);

        db = FirebaseFirestore.getInstance();

        buttonAddClub.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String winsStr = editTextWins.getText().toString().trim();
            String lossesStr = editTextLosses.getText().toString().trim();
            String drawsStr = editTextDraws.getText().toString().trim();
            String pointsStr = editTextPoints.getText().toString().trim();

            if (name.isEmpty() || winsStr.isEmpty() || lossesStr.isEmpty() || drawsStr.isEmpty() || pointsStr.isEmpty()) {
                Toast.makeText(AddTableActivity.this, "Molimo ispunite sva polja.", Toast.LENGTH_SHORT).show();
                return;
            }

            int wins = Integer.parseInt(winsStr);
            int losses = Integer.parseInt(lossesStr);
            int draws = Integer.parseInt(drawsStr);
            int points = Integer.parseInt(pointsStr);

            addTable(name, wins, losses, draws, points);
        });
    }

    private void addTable(String name, int wins, int losses, int draws, int points) {
        String id = db.collection("tables").document().getId();
        Table table = new Table(id, name, wins, losses, draws, points);

        db.collection("tables").document(id).set(table)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(AddTableActivity.this, "Klub dodan u tablicu.", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(AddTableActivity.this, "Error kod dodavanja kluba u tablicu", Toast.LENGTH_SHORT).show());
    }
}
