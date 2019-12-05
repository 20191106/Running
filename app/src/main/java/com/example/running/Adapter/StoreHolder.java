package com.example.running.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StoreHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView name;
    TextView detail;
    TextView price;

    public StoreHolder(@NonNull View itemView) {
        super(itemView);
        //TODO
    }
}
