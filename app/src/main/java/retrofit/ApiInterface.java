package retrofit;
 
import json.GameData;
import json.PlayerInfo;
import retrofit2.Call;
import retrofit2.http.GET;
 
 
public interface ApiInterface {
    @GET("2ewt6r22zo4qwgx/gameData.json")
    Call<GameData> getGameData();
 
    @GET("5zz3hibrxpspoe5/playerInfo.json")
    Call<PlayerInfo> getPlayerInfo();
}