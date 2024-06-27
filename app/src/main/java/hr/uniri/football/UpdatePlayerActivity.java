package hr.uniri.football;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class UpdatePlayerActivity extends AppCompatActivity {
    private EditText editTextName, editTextSurname, editTextCategory, editTextPosition, editTextNumber;
    private FirebaseFirestore db;
    private String playerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_player);

        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextPosition = findViewById(R.id.editTextPosition);
        editTextNumber = findViewById(R.id.editTextNumber);
        Button UpdatePlayer = findViewById(R.id.btnUpdatePlayer);
        Button DeletePlayer = findViewById(R.id.btnDeletePlayer);

        db = FirebaseFirestore.getInstance();
        playerId = getIntent().getStringExtra("playerId");

        loadPlayerData();

        UpdatePlayer.setOnClickListener(v -> updatePlayer());
        DeletePlayer.setOnClickListener(v -> deletePlayer());
    }

    private void loadPlayerData() {
        db.collection("players").document(playerId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Player player = documentSnapshot.toObject(Player.class);
                        if (player != null) {
                            editTextName.setText(player.getName());
                            editTextSurname.setText(player.getSurname());
                            editTextCategory.setText(player.getCategory());
                            editTextPosition.setText(player.getPosition());
                            editTextNumber.setText(String.valueOf(player.getNumber()));
                        }
                    }
                });
    }

    private void updatePlayer() {
        String name = editTextName.getText().toString();
        String surname = editTextSurname.getText().toString();
        String category = editTextCategory.getText().toString();
        String position = editTextPosition.getText().toString();
        int number = Integer.parseInt(editTextNumber.getText().toString());

        db.collection("players").document(playerId)
                .update("name", name, "surname", surname, "category", category, "position", position, "number", number)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(UpdatePlayerActivity.this, "Igrač ažuriran", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(UpdatePlayerActivity.this, "Error kod ažuriranja igrača", Toast.LENGTH_SHORT).show());
    }

    private void deletePlayer() {
        db.collection("players").document(playerId).delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(UpdatePlayerActivity.this, "Igarč obrisan", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(UpdatePlayerActivity.this, "Error kod brisanja igrača", Toast.LENGTH_SHORT).show());
    }
}
