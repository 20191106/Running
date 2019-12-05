package com.example.running;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.running.Adapter.ItemAdapter;
import com.example.running.Model.MyItem;

import java.util.ArrayList;

public class FragItem extends BaseFragment {
    ImageView head, myHead, body, myBody, weapon, myWeapon;
    ImageView storeBtn;
    ListView listView;

    ItemAdapter itemAdapter;
    ArrayList<MyItem> myItem = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_item, container, false);
        final MainActivity m = (MainActivity)getActivity();

        head = v.findViewById(R.id.item_head);
        myHead = v.findViewById(R.id.item_myHead);
        body = v.findViewById(R.id.item_body);
        myBody = v.findViewById(R.id.item_myBody);
        weapon = v.findViewById(R.id.item_weapon);
        myWeapon = v.findViewById(R.id.item_myWeapon);
        storeBtn = v.findViewById(R.id.item_storeBtn);
        listView = v.findViewById(R.id.item_listView);

        Glide.with(m).load(R.drawable.headtap).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(head);
        Glide.with(m).load(R.drawable.bodytap).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(body);
        Glide.with(m).load(R.drawable.weapontap).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(weapon);
        Glide.with(m).load(R.drawable.smallstorebutton).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(storeBtn);

        changeArray(m.fragLogin.myInfo.heads);
        itemAdapter = new ItemAdapter(m, myItem);
        listView.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();

        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeArray(m.fragLogin.myInfo.heads);
                itemAdapter.notifyDataSetChanged();
            }
        });
        body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeArray(m.fragLogin.myInfo.bodies);
                itemAdapter.notifyDataSetChanged();
            }
        });
        weapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeArray(m.fragLogin.myInfo.weapons);
                itemAdapter.notifyDataSetChanged();
            }
        });
        storeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.popStore();
            }
        });

        return v;
    }

    private void changeArray(ArrayList<MyItem> a){
        myItem.clear();
        for (int i = 0; i < a.size(); i++) {
            myItem.add(a.get(i));
        }
    }
}
