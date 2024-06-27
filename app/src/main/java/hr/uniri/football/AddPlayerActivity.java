package hr.uniri.football;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddPlayerActivity extends AppCompatActivity {
    private EditText editTextName, editTextSurname, editTextCategory, editTextPosition, editTextNumber;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextPosition = findViewById(R.id.editTextPosition);
        editTextNumber = findViewById(R.id.editTextNumber);

        Button dodajIgraca = findViewById(R.id.btnDodajIgraca);

        db = FirebaseFirestore.getInstance();

        dodajIgraca.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String surname = editTextSurname.getText().toString().trim();
            String category = editTextCategory.getText().toString().trim();
            String position = editTextPosition.getText().toString().trim();
            String numberStr = editTextNumber.getText().toString().trim();

            if (name.isEmpty() || surname.isEmpty() || category.isEmpty() || position.isEmpty() || numberStr.isEmpty()) {
                Toast.makeText(AddPlayerActivity.this, "Molimo ispunite sva polja.", Toast.LENGTH_SHORT).show();
                return;
            }

            int number;
            try {
                number = Integer.parseInt(numberStr);
            } catch (NumberFormatException e) {
                Toast.makeText(AddPlayerActivity.this, "Broj mora biti tipa int", Toast.LENGTH_SHORT).show();
                return;
            }

            addPlayer(name, surname, category, position, number);
        });
    }
    private void addPlayer(String name, String surname, String category, String position, int number) {
        String id = db.collection("players").document().getId();
        Player player = new Player(id, name, surname, category, position, number);

        db.collection("players").document(id).set(player)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(AddPlayerActivity.this, "Igrač dodan", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(AddPlayerActivity.this, "Error kod dodavanja igrača", Toast.LENGTH_SHORT).show());
    }
}
