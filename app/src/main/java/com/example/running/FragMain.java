package com.example.running;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.running.Adapter.RankAdapter;
import com.example.running.Model.MyInfo;
import com.example.running.Model.Ranker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class FragMain extends BaseFragment {
    ImageView settingBtn;
    ImageView startBtn;
    ImageView rankBtn;
    ImageView itemBtn;
    ImageView storeBtn;

    ArrayList<Ranker> rankers = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_main, container, false);
        final MainActivity m = (MainActivity)getActivity();

        settingBtn = v.findViewById(R.id.main_settingBtn);
        startBtn = v.findViewById(R.id.main_startBtn);
        rankBtn = v.findViewById(R.id.main_rankBtn);
        itemBtn = v.findViewById(R.id.main_itemBtn);
        storeBtn = v.findViewById(R.id.main_storeBtn);
        Glide.with(m).load(R.drawable.jumpbutton).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(settingBtn);
        Glide.with(m).load(R.drawable.jumpbutton).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(startBtn);
        Glide.with(m).load(R.drawable.jumpbutton).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(rankBtn);
        Glide.with(m).load(R.drawable.jumpbutton).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(itemBtn);
        Glide.with(m).load(R.drawable.jumpbutton).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(storeBtn);

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.popGame();
            }
        });
        rankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popRank(m);
            }
        });
        itemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        storeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }

    private void popRank(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v2 = (View)inflater.inflate(R.layout.pop_rank, null);

        AlertDialog.Builder ab = new AlertDialog.Builder((context));
        ab.setView(v2);

        getRankersInfo(context);
        ListView rank_listView = v2.findViewById(R.id.pop_rank_listView);
        RankAdapter rankAdapter = new RankAdapter(getActivity(), rankers);
        rank_listView.setAdapter(rankAdapter);

        AlertDialog temp = ab.create();
        temp.show();
    }

    private void getRankersInfo(final Context context){
        RequestQueue stringRequest = Volley.newRequestQueue(context);
        String temp = "http://jeho.dothome.co.kr/myDir/running/get_rank.php";
        StringRequest myReq = new StringRequest(Request.Method.GET,
                temp,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data = new JSONObject(response);
                            String result = data.getString("result");
                            if (result.equals("OK")){
                                JSONArray temp = data.getJSONArray("data_list");
                                rankers.clear();
                                for (int i = 0; i < temp.length(); i++) {
                                    rankers.add(new Ranker(i+1, Integer.parseInt(temp.getJSONObject(i).getString("head")),
                                            Integer.parseInt(temp.getJSONObject(i).getString("body")),
                                            Integer.parseInt(temp.getJSONObject(i).getString("weapon")),
                                            temp.getJSONObject(i).getString("name"),
                                            Integer.parseInt(temp.getJSONObject(i).getString("topscore"))));
                                }
                            }
                            else{
                                Toast.makeText(context, "Request Error (getrankresult)", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Request Error (getrankcaught)", Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Request Error : getrank", Toast.LENGTH_LONG).show();
                    }
                });
        stringRequest.add(myReq);
    }
}
