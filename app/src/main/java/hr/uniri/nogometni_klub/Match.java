package hr.uniri.nogometni_klub;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Match {
    @PrimaryKey
    public int id;
    public String homeTeam;
    public String awayTeam;
    public int homeTeamScore;
    public int awayTeamScore;
    // Dodajte ostale potrebne atribute utakmice
}
