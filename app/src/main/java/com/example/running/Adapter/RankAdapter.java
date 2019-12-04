package com.example.running.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.running.Model.Ranker;
import com.example.running.R;

import java.util.ArrayList;

public class RankAdapter extends ArrayAdapter {
    LayoutInflater lnf;
    ArrayList<Ranker> rankers;
    Activity context;

    public RankAdapter(Activity context, ArrayList<Ranker> rankers) {
        super(context, R.layout.row_rank, rankers);
        this.context = context;
        lnf = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.rankers = rankers;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return rankers.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return rankers.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        RankHolder viewHolder;

        if (convertView == null) {
            convertView = lnf.inflate(R.layout.row_rank, parent, false);
            viewHolder = new RankHolder();
            viewHolder.rank = convertView.findViewById(R.id.row_rank_rank);
            viewHolder.image = convertView.findViewById(R.id.row_rank_image);
            viewHolder.name = convertView.findViewById(R.id.row_rank_name);
            viewHolder.topscore = convertView.findViewById(R.id.row_rank_topscore);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (RankHolder)convertView.getTag();
        }

        viewHolder.rank.setText(rankers.get(position).rank + "위");
        Glide.with(context).load(R.drawable.jumpbutton).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(viewHolder.image);
        viewHolder.name.setText(rankers.get(position).name);
        viewHolder.topscore.setText(rankers.get(position).topscore + "점");

        return convertView;
    }
}
