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

    String id, ime, prezime, godine, pozicija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editIme = findViewById(R.id.editIme2);
        editPrezime = findViewById(R.id.editPrezime2);
        editGodine = findViewById(R.id.editGodine2);
        editPozicija = findViewById(R.id.editPozicija2);
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
        if(getIntent().hasExtra("id") && getIntent().hasExtra("ime") &&
                getIntent().hasExtra("prezime") && getIntent().hasExtra("godine") && getIntent().hasExtra("pozicija")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            ime = getIntent().getStringExtra("ime");
            prezime = getIntent().getStringExtra("prezime");
            godine = String.valueOf(Integer.parseInt(getIntent().getStringExtra("godine")));
            pozicija = getIntent().getStringExtra("pozicija");

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