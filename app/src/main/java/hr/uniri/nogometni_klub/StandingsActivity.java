package hr.uniri.nogometni_klub;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class StandingsActivity extends AppCompatActivity {

    RecyclerView recyclerViewStandings;
    ArrayList<Standings> standingsList;
    StandingsAdapter standingsAdapter;
    AppDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);

        recyclerViewStandings = findViewById(R.id.recyclerViewStandings);
        myDB = new AppDatabase(this);

        standingsList = new ArrayList<>();
        standingsAdapter = new StandingsAdapter(this, standingsList);

        recyclerViewStandings.setAdapter(standingsAdapter);
        recyclerViewStandings.setLayoutManager(new LinearLayoutManager(this));

        displayStandings();
    }

    void displayStandings() {
        Cursor cursor = myDB.readAllStandingsSortedByPoints();
        if (cursor.getCount() == 0) {
            Log.d("StandingsActivity", "No data found");
        } else {
            standingsList.clear();
            int rank = 1;
            while (cursor.moveToNext()) {
                standingsList.add(new Standings(
                        rank,
                        cursor.getString(1),
                        cursor.getInt(2)
                ));
                rank++;
            }
        }
        standingsAdapter.notifyDataSetChanged();
    }
}
