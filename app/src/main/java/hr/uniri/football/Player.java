package hr.uniri.football;

public class Player {
    private String id;
    private String name;
    private String surname;
    private String category;
    private String position;
    private int number;

    public Player() {
    }

    public Player(String id, String name, String surname, String category, String position, int number) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.category = category;
        this.position = position;
        this.number = number;
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

    public String getSurname() {
        return surname;
    }

    public String getCategory() {
        return category;
    }

    public String getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }

}
