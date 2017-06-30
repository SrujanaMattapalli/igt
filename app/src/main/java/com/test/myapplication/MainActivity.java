package com.test.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import json.Game;
import json.GameData;
import json.PlayerInfo;
import recyclerview.DataAdapter;
import retrofit.ApiClient;
import retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Game> gameList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
    private TextView mName;
    private TextView mBalance;
    private TextView mLastLoginDate;
    private ImageView mAvatarLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new DataAdapter(gameList, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mName = (TextView) findViewById(R.id.name);
        mBalance = (TextView) findViewById(R.id.balance);
        mLastLoginDate = (TextView) findViewById(R.id.last_login);
        mAvatarLink = (ImageView) findViewById(R.id.image);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<GameData> call = apiService.getGameData();
        call.enqueue(new Callback<GameData>() {
            @Override
            public void onResponse(Call<GameData> call, Response<GameData> response) {
                ArrayList<Game> gameData = response.body().getData();
                Log.d("", "data " + gameData.size());
                mAdapter = new DataAdapter(gameData, MainActivity.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GameData> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });

        apiService.getPlayerInfo().enqueue(new Callback<PlayerInfo>() {
            @Override
            public void onResponse(Call<PlayerInfo> call, Response<PlayerInfo> response) {
                mName.setText(response.body().getName());
                mBalance.setText(response.body().getBalance());
                mLastLoginDate.setText(response.body().getLastLogindate());
                Glide.with(MainActivity.this).load(response.body().getAvatarLink()).into(mAvatarLink);
            }

            @Override
            public void onFailure(Call<PlayerInfo> call, Throwable t) {
                Log.e("", t.toString());
            }
        });
    }
}
