package hr.uniri.football;
public class Match {
    public String id;
    private String homeTeam;
    private String awayTeam;
    private String homeScore;
    private String awayScore;

    public Match() {
    }

    public Match(String id, String homeTeam, String awayTeam, String homeScore, String awayScore) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }


    public String getAwayTeam() {
        return awayTeam;
    }


    public String getHomeScore() {
        return homeScore;
    }


    public String getAwayScore() {
        return awayScore;
    }

}
