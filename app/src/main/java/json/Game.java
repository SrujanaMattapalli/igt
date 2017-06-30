package json;
 
public class Game {
    private String name, jackpot, date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game(String name, String jackpot, String date) {
        this.name = name;
        this.jackpot = jackpot;
        this.date = date;
    }
}