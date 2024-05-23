package hr.uniri.nogometni_klub;

public class Standings {
    private int rank;
    private String imeKluba;
    private int bodovi;

    public Standings(int rank, String imeKluba, int bodovi) {
        this.rank = rank;
        this.imeKluba = imeKluba;
        this.bodovi = bodovi;
    }

    public int getRank() {
        return rank;
    }

    public String getImeKluba() {
        return imeKluba;
    }

    public int getBodovi() {
        return bodovi;
    }
}
