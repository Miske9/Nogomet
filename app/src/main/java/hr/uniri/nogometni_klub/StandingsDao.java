package hr.uniri.nogometni_klub;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
@Dao
public interface StandingsDao {
    @Insert
    void insert(Note note);
    @Query("SELECT * FROM Note")
    List<Note> getAllStandings();
}
