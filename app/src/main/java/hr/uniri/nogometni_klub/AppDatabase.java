package hr.uniri.nogometni_klub;
import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Player.class, Match.class, Standings.class, Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDao playerDao();
    public abstract MatchDao matchDao();
    public abstract StandingsDao standingsDao();
}