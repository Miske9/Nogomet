package hr.uniri.football;
public class Table {
    private String id;
    private String name;
    private int wins;
    private int losses;
    private int draws;
    private int points;

    public Table() {
    }

    public Table(String id, String name, int wins, int losses, int draws, int points) {
        this.id = id;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.points = points;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getWins() {
        return wins;
    }


    public int getLosses() {
        return losses;
    }


    public int getDraws() {
        return draws;
    }


    public int getPoints() {
        return points;
    }

}
