package com.test.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import json.PlayerInfo;
import retrofit.ApiClient;
import retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewActivity extends AppCompatActivity {

    private TextView mName;
    private TextView mBalance;
    private ImageView mAvatarLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);
        mName = (TextView) findViewById(R.id.name);
        mBalance = (TextView) findViewById(R.id.balance);
        mAvatarLink = (ImageView) findViewById(R.id.image);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        apiService.getPlayerInfo().enqueue(new Callback<PlayerInfo>() {
            @Override
            public void onResponse(Call<PlayerInfo> call, Response<PlayerInfo> response) {
                mName.setText(response.body().getName());
                mBalance.setText(response.body().getBalance());
                Glide.with(ViewActivity.this).load(response.body().getAvatarLink()).into(mAvatarLink);
            }

            @Override
            public void onFailure(Call<PlayerInfo> call, Throwable t) {
                Log.e("", t.toString());
            }
        });
    }


}
