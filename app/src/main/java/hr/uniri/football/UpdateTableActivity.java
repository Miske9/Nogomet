package hr.uniri.football;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateTableActivity extends AppCompatActivity {
    private EditText editTextName, editTextWins, editTextLosses, editTextDraws, editTextPoints;
    private FirebaseFirestore db;
    private String tableId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_table);

        editTextName = findViewById(R.id.editTextName);
        editTextWins = findViewById(R.id.editTextWins);
        editTextDraws = findViewById(R.id.editTextDraws);
        editTextLosses = findViewById(R.id.editTextLosses);
        editTextPoints = findViewById(R.id.editTextPoints);
        Button UpdateTable = findViewById(R.id.btnUpdateTable);
        Button DeleteTable = findViewById(R.id.btnDeleteTable);

        db = FirebaseFirestore.getInstance();
        tableId = getIntent().getStringExtra("tableId");

        loadTableData();

        UpdateTable.setOnClickListener(v -> updateTable());
        DeleteTable.setOnClickListener(v -> deleteTable());
    }

    private void loadTableData() {
        db.collection("tables").document(tableId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Table table = documentSnapshot.toObject(Table.class);
                        if (table != null) {
                            editTextName.setText(table.getName());
                            editTextWins.setText(String.valueOf(table.getWins()));
                            editTextDraws.setText(String.valueOf(table.getDraws()));
                            editTextLosses.setText(String.valueOf(table.getLosses()));
                            editTextPoints.setText(String.valueOf(table.getPoints()));
                        }
                    } else {
                        Toast.makeText(UpdateTableActivity.this, "Dokument ne postoji", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(UpdateTableActivity.this, "Error kod dohvaćanja podataka tablice", Toast.LENGTH_SHORT).show());
    }

    private void updateTable() {
        String name = editTextName.getText().toString().trim();
        if (name.isEmpty()) {
            Toast.makeText(this, "Ime ne može biti prazno", Toast.LENGTH_SHORT).show();
            return;
        }

        int wins, losses, draws, points;
        try {
            wins = Integer.parseInt(editTextWins.getText().toString().trim());
            draws = Integer.parseInt(editTextDraws.getText().toString().trim());
            losses = Integer.parseInt(editTextLosses.getText().toString().trim());
            points = Integer.parseInt(editTextPoints.getText().toString().trim());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Unesite broj", Toast.LENGTH_SHORT).show();
            return;
        }

        Table updatedTable = new Table(tableId, name, wins, losses, draws, points);

        db.collection("tables").document(tableId)
                .set(updatedTable)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(UpdateTableActivity.this, "Tablica ažurirana", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(UpdateTableActivity.this, "Error kod ažuriranja tablice", Toast.LENGTH_SHORT).show());
    }

    private void deleteTable() {
        db.collection("tables").document(tableId).delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(UpdateTableActivity.this, "Tablica obrisana", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(UpdateTableActivity.this, "Error kod brisanja tablice", Toast.LENGTH_SHORT).show());
    }
}
