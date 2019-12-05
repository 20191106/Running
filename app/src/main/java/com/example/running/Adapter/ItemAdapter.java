package com.example.running.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.running.Model.MyItem;
import com.example.running.R;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter {
    LayoutInflater lnf;
    ArrayList<MyItem> items;
    Activity context;

    public ItemAdapter(Activity context, ArrayList<MyItem> items) {
        super(context, R.layout.row_item, items);
        this.context = context;
        lnf = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ItemHolder viewHolder;

        if (convertView == null) {
            convertView = lnf.inflate(R.layout.row_item, parent, false);
            viewHolder = new ItemHolder();

            viewHolder.image = convertView.findViewById(R.id.row_item_image);
            viewHolder.name = convertView.findViewById(R.id.row_item_name);
            viewHolder.detail = convertView.findViewById(R.id.row_item_detail);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ItemHolder) convertView.getTag();
        }

        Glide.with(context).load(items.get(position).imageId).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(viewHolder.image);
        viewHolder.name.setText(items.get(position).name);
        viewHolder.detail.setText(items.get(position).detail);

        return convertView;
    }
}
