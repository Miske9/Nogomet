package hr.uniri.nogometni_klub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText editIme, editPrezime, editGodine, editPozicija;
    Button update_button, delete_button;

    String id;
    String ime;
    String prezime;
    String godine;
    String pozicija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editIme = findViewById(R.id.editIme);
        editPrezime = findViewById(R.id.editPrezime);
        editGodine = findViewById(R.id.editGodine);
        editPozicija = findViewById(R.id.editPozicija);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(ime);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                AppDatabase appDatabase = new AppDatabase(UpdateActivity.this);
                ime = editIme.getText().toString().trim();
                prezime = editPrezime.getText().toString().trim();
                godine = editGodine.getText().toString().trim();
                pozicija = editPozicija.getText().toString().trim();
                appDatabase.updateData(id, ime, prezime, Integer.parseInt(godine), pozicija);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("player_ID") && getIntent().hasExtra("ime_igraca") &&
                getIntent().hasExtra("prezime_igraca") && getIntent().hasExtra("godine_igraca") && getIntent().hasExtra("pozicija_igraca")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("player_ID");
            ime = getIntent().getStringExtra("ime_igraca");
            prezime = getIntent().getStringExtra("prezime_igraca");
            godine = String.valueOf(Integer.parseInt(getIntent().getStringExtra("godine_igraca")));
            pozicija = getIntent().getStringExtra("pozicija_igraca");

            //Setting Intent Data
            editIme.setText(ime);
            editPrezime.setText(prezime);
            editGodine.setText(godine);
            editPozicija.setText(pozicija);
            Log.d("stev", ime+" "+prezime+" "+godine+" "+pozicija);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + ime + " " + prezime + "?");
        builder.setMessage("Are you sure you want to delete " + ime + " " + prezime + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AppDatabase appDatabase = new AppDatabase(UpdateActivity.this);
                appDatabase.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}