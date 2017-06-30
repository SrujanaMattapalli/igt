package json;

import java.util.ArrayList;

public class GameData {

    String response;
    String currency;
    ArrayList<Game> data;
    public ArrayList<Game> getData() {
        return data;
    }

    public void setData(ArrayList<Game> data) {
        this.data = data;
    }



}
