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

public class UpdateMatchActivity extends AppCompatActivity {

    EditText editDomaciKlub, editGostKlub, editRezultat;
    Button update_button, delete_button;

    String id, domaci_klub, gost_klub, rezultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_match);

        editDomaciKlub = findViewById(R.id.editDomaciKlub2);
        editGostKlub = findViewById(R.id.editGostKlub2);
        editRezultat = findViewById(R.id.editRezultat2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(domaci_klub);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase appDatabase = new AppDatabase(UpdateMatchActivity.this);
                domaci_klub = editDomaciKlub.getText().toString().trim();
                gost_klub= editGostKlub.getText().toString().trim();
                rezultat = editRezultat.getText().toString().trim();
                appDatabase.updateMatchData(id, domaci_klub, gost_klub, rezultat);
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
        if(getIntent().hasExtra("id") && getIntent().hasExtra("domaci_klub") &&
                getIntent().hasExtra("gost_klub") && getIntent().hasExtra("rezultat")){
            id = getIntent().getStringExtra("id");
            domaci_klub= getIntent().getStringExtra("domaci_klub");
            gost_klub = getIntent().getStringExtra("gost_klub");
            rezultat = getIntent().getStringExtra("rezultat");

            //Setting Intent Data
            editDomaciKlub.setText(domaci_klub);
            editGostKlub.setText(gost_klub);
            editRezultat.setText(rezultat);
            Log.d("stev", domaci_klub+" "+gost_klub+" "+rezultat);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + domaci_klub + " " +gost_klub + "?");
        builder.setMessage("Are you sure you want to delete " +domaci_klub + " " + gost_klub + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AppDatabase appDatabase = new AppDatabase(UpdateMatchActivity.this);
                appDatabase.deleteOneRowFromMatch(id);
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