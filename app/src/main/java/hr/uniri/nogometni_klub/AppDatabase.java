package hr.uniri.nogometni_klub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.util.Random;

class AppDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "FootballLibrary.db";
    private static final int DATABASE_VERSION = 3;

    private static final String PLAYER_TABLE_NAME = "player";
    private static final String PLAYER_COLUMN_ID = "player_ID";
    private static final String PLAYER_COLUMN_IME = "ime_igraca";
    private static final String PLAYER_COLUMN_PREZIME = "prezime_igraca";
    private static final String PLAYER_COLUMN_GODINE = "godine_igraca";
    private static final String PLAYER_COLUMN_POZICIJA = "pozicija_igraca";

    private static final String MATCH_TABLE_NAME = "utakmica";
    private static final String MATCH_COLUMN_ID = "match_ID";
    private static final String MATCH_COLUMN_DOMACI_KLUB = "domaci_klub";
    private static final String MATCH_COLUMN_GOST_KLUB = "gost_klub";
    private static final String MATCH_COLUMN_REZULTAT = "rezultat";

    private static final String STANDING_TABLE_NAME = "poredak";
    private static final String STANDING_COLUMN_ID = "club_ID";
    private static final String STANDING_COLUMN_IME_KLUBA = "ime_kluba";
    private static final String STANDING_COLUMN_BODOVI = "bodovi";
    AppDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + PLAYER_TABLE_NAME +
                " (" + PLAYER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PLAYER_COLUMN_IME + " TEXT, " +
                PLAYER_COLUMN_PREZIME + " TEXT, " +
                PLAYER_COLUMN_GODINE + " INT, " +
                PLAYER_COLUMN_POZICIJA + " TEXT);";
        db.execSQL(query);

        String matchQuery = "CREATE TABLE " + MATCH_TABLE_NAME +
                " (" + MATCH_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MATCH_COLUMN_DOMACI_KLUB + " TEXT, " +
                MATCH_COLUMN_GOST_KLUB + " TEXT, " +
                MATCH_COLUMN_REZULTAT + " INT);";
        String standingsQuery = "CREATE TABLE " + STANDING_TABLE_NAME +
                " (" + STANDING_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STANDING_COLUMN_IME_KLUBA + " TEXT, " +
                STANDING_COLUMN_BODOVI + " INT);";
        db.execSQL(standingsQuery);
        db.execSQL(matchQuery);
        insertInitialStandingsData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            String matchQuery = "CREATE TABLE " + MATCH_TABLE_NAME +
                    " (" + MATCH_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MATCH_COLUMN_DOMACI_KLUB + " TEXT, " +
                    MATCH_COLUMN_GOST_KLUB + " TEXT, " +
                    MATCH_COLUMN_REZULTAT + " INT);";
            db.execSQL(matchQuery);
        }
        if (oldVersion < 3) {
            String standingsQuery = "CREATE TABLE " + STANDING_TABLE_NAME +
                    " (" + STANDING_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    STANDING_COLUMN_IME_KLUBA + " TEXT, " +
                    STANDING_COLUMN_BODOVI + " INT);";
            db.execSQL(standingsQuery);
            insertInitialStandingsData(db);
        }
    }
    void addPlayer(String ime, String prezime, int godine, String pozicija){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PLAYER_COLUMN_IME, ime);
        cv.put(PLAYER_COLUMN_PREZIME, prezime);
        cv.put(PLAYER_COLUMN_GODINE, godine);
        cv.put(PLAYER_COLUMN_POZICIJA, pozicija);
        long result = db.insert(PLAYER_TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    void addMatch(String domaci_klub, String gost_klub, int rezultat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MATCH_COLUMN_DOMACI_KLUB, domaci_klub);
        cv.put(MATCH_COLUMN_GOST_KLUB, gost_klub);
        cv.put(MATCH_COLUMN_REZULTAT, rezultat);
        long result = db.insert(MATCH_TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllPlayerData(){
        String query = "SELECT * FROM " + PLAYER_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor readAllMatchData(){
        String query = "SELECT * FROM " + MATCH_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public Cursor readAllStandingsSortedByPoints() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + STANDING_TABLE_NAME + " ORDER BY " + STANDING_COLUMN_BODOVI + " DESC";
        return db.rawQuery(query, null);
    }
    private void insertInitialStandingsData(SQLiteDatabase db) {
        String[] clubs = {"Klub A", "Klub B", "Klub C", "Klub D", "Klub E", "Klub F", "Klub G", "Klub H", "Klub I", "Klub J"};
        Random random = new Random();

        for (String club : clubs) {
            ContentValues cv = new ContentValues();
            cv.put(STANDING_COLUMN_IME_KLUBA, club);
            cv.put(STANDING_COLUMN_BODOVI, random.nextInt(101)); // Random bodovi između 0 i 100
            long result = db.insert(STANDING_TABLE_NAME, null, cv);
            if (result == -1) {
                Log.d("insertInitialStandingsData", "Failed to insert data for " + club);
            } else {
                Log.d("insertInitialStandingsData", "Data inserted for " + club);
            }
        }
    }

    void updatePlayerData(String row_id, String ime, String prezime, int godine, String pozicija){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_COLUMN_IME, ime);
        cv.put(PLAYER_COLUMN_PREZIME, prezime);
        cv.put(PLAYER_COLUMN_GODINE, godine);
        cv.put(PLAYER_COLUMN_POZICIJA, pozicija);

        long result = db.update(PLAYER_TABLE_NAME, cv, PLAYER_COLUMN_ID + "=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    void updateMatchData(String row_id, String domaci_klub, String gost_klub, int rezultat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MATCH_COLUMN_DOMACI_KLUB, domaci_klub);
        cv.put(MATCH_COLUMN_GOST_KLUB, gost_klub);
        cv.put(MATCH_COLUMN_REZULTAT, rezultat);

        long result = db.update(MATCH_TABLE_NAME, cv, MATCH_COLUMN_ID + "=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRowFromPlayer(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(PLAYER_TABLE_NAME, PLAYER_COLUMN_ID + "=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
            Cursor cursor = readAllPlayerData();
            if (cursor.getCount() == 0) {
                db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + PLAYER_TABLE_NAME + "'");
            } else {
                db.execSQL("UPDATE " + PLAYER_TABLE_NAME + " SET " + PLAYER_COLUMN_ID + " = " + PLAYER_COLUMN_ID + " - 1 WHERE " + PLAYER_COLUMN_ID + " > " + row_id);
            }
        }
    }
    void deleteOneRowFromMatch(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(MATCH_TABLE_NAME, MATCH_COLUMN_ID + "=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
            Cursor cursor = readAllMatchData();
            if (cursor.getCount() == 0) {
                db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + MATCH_TABLE_NAME + "'");
            } else {
                db.execSQL("UPDATE " + MATCH_TABLE_NAME + " SET " + MATCH_COLUMN_ID + " = " + MATCH_COLUMN_ID + " - 1 WHERE " + MATCH_COLUMN_ID + " > " + row_id);
            }
        }
    }

}
