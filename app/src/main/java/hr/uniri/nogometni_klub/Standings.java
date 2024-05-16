package hr.uniri.nogometni_klub;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Standings {
    @PrimaryKey
    public int id;
    public String team;
    public int points;
    // Dodajte ostale potrebne atribute tablice
}
