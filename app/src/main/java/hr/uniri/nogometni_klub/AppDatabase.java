package hr.uniri.nogometni_klub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

class AppDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "FootballLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "player_ID";
    private static final String COLUMN_IME = "ime_igraca";
    private static final String COLUMN_PREZIME = "prezime_igraca";
    private static final String COLUMN_GODINE = "godine_igraca";
    private static final String COLUMN_POZICIJA = "pozicija_igraca"; // Promijenjen naziv kolone

    AppDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IME + " TEXT, " +
                COLUMN_PREZIME + " TEXT, " +
                COLUMN_GODINE + " INT, " +
                COLUMN_POZICIJA + " TEXT);"; // Promijenjen naziv kolone
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addPlayer(String ime, String prezime, int godine, String pozicija){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_IME, ime);
        cv.put(COLUMN_PREZIME, prezime);
        cv.put(COLUMN_GODINE, godine);
        cv.put(COLUMN_POZICIJA, pozicija); // Ispravljena kolona
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String ime, String prezime, int godine, String pozicija){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_IME, ime);
        cv.put(COLUMN_PREZIME, prezime);
        cv.put(COLUMN_GODINE, godine);
        cv.put(COLUMN_POZICIJA, pozicija); // Ispravljena kolona

        long result = db.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{row_id}); // Koristi COLUMN_ID
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{row_id}); // Koristi COLUMN_ID
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
