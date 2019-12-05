package com.example.running;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class FragStore extends BaseFragment {
    ImageView head, body, weapon;
    ImageView itemBtn;

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_store, container, false);
        final MainActivity m = (MainActivity)getActivity();

        head = v.findViewById(R.id.store_head);
        body = v.findViewById(R.id.store_body);
        weapon = v.findViewById(R.id.store_weapon);
        itemBtn = v.findViewById(R.id.store_itemBtn);
        recyclerView = v.findViewById(R.id.store_recyclerView);

        Glide.with(m).load(R.drawable.headtap).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(head);
        Glide.with(m).load(R.drawable.bodytap).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(body);
        Glide.with(m).load(R.drawable.weapontap).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(weapon);
        Glide.with(m).load(R.drawable.smallitembutton).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(itemBtn);



        return v;
    }
}
