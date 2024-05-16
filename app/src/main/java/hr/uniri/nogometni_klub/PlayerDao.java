package hr.uniri.nogometni_klub;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface PlayerDao {

    @Insert
    void insert(Player player);
}

