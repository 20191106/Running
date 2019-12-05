package com.example.running.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.running.Model.MyItem;
import com.example.running.R;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreHolder> {
    ArrayList<MyItem> items;

    public StoreAdapter(ArrayList<MyItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public StoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context  = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.frag_store, parent, false);
        StoreHolder holder = new StoreHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreHolder holder, int position) {
        holder.name.setText(items.get(position).name);
        //TODO
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
