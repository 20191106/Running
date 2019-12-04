package com.example.running;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class BaseFragment extends Fragment {
    private void popSetting(){

    }

    public void popNotice(String string, Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v2 = (View)inflater.inflate(R.layout.pop_notice, null);

        AlertDialog.Builder ab = new AlertDialog.Builder((context));
        ab.setView(v2);

        TextView text = v2.findViewById(R.id.notice_text);
        ImageView yesBtn = v2.findViewById(R.id.notice_yesBtn);

        text.setText(string);
        Glide.with(context).load(R.drawable.jumpbutton).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(yesBtn);

        final AlertDialog temp = ab.create();

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp.dismiss();
            }
        });
        temp.show();
    }
}
