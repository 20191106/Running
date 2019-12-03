package com.example.running;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FragGame extends BaseFragment {
    MainCanvas can;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_game, container, false);
        MainActivity m = (MainActivity)getActivity();

        can = new MainCanvas(m);
        can = v.findViewById(R.id.mainCanvas);

        return v;
    }
}
