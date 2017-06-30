package recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.myapplication.R;
import com.test.myapplication.ViewActivity;

import java.util.List;

import json.Game;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private List<Game> gameList;
    private Context context;


    public DataAdapter(List<Game> gameDataList, Context context) {
        this.gameList = gameDataList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Game game = gameList.get(position);
        TextView name = holder.name;
        name.setText(game.getName());
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ViewActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
        }
    }
}