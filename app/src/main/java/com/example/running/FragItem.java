package com.example.running;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class FragItem extends BaseFragment {
    ImageView head, myHead, body, myBody, weapon, myWeapon;
    ImageView storeBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_main, container, false);
        final MainActivity m = (MainActivity)getActivity();

        head = v.findViewById(R.id.item_head);
        myHead = v.findViewById(R.id.item_myHead);
        body = v.findViewById(R.id.item_body);
        myBody = v.findViewById(R.id.item_myBody);
        weapon = v.findViewById(R.id.item_weapon);
        myWeapon = v.findViewById(R.id.item_myWeapon);

        Glide.with(m).load(R.drawable.headtap).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(head);

        return v;
    }
}
