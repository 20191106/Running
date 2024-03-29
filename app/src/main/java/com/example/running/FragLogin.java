package com.example.running;

import android.content.Context;
import android.media.Image;
import android.opengl.ETC1;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.running.Model.MyInfo;
import com.example.running.Model.MyItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragLogin extends BaseFragment {
    EditText idEt;
    EditText pwdEt;
    ImageView signInBtn;
    ImageView signUpBtn;
    MyInfo myInfo;
    ArrayList<MyItem> heads = new ArrayList<>();
    ArrayList<MyItem> bodies = new ArrayList<>();
    ArrayList<MyItem> weapons = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_login, container, false);
        final MainActivity m = (MainActivity)getActivity();

        idEt = v.findViewById(R.id.login_idEt);
        pwdEt = v.findViewById(R.id.login_pwdEt);
        signInBtn = v.findViewById(R.id.login_signInBtn);
        signUpBtn = v.findViewById(R.id.login_signUpBtn);
        Glide.with(m).load(R.drawable.loginbutton).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(signInBtn);
        Glide.with(m).load(R.drawable.signupbutton).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(signUpBtn);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idEt.getText().toString().trim();
                String pwd = pwdEt.getText().toString().trim();
                RequestQueue stringRequest = Volley.newRequestQueue(m);
                String temp = "http://jeho.dothome.co.kr/myDir/running/sign_in_item.php?id=" + id + "&pwd=" + pwd;
                StringRequest myReq = new StringRequest(Request.Method.GET,
                        temp,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject data = new JSONObject(response);
                                    String result = data.getString("result");
                                    if(result.equals("OK")){
                                        JSONObject temp = data.getJSONObject("data");
                                        JSONArray item_list = data.getJSONArray("item_list");
                                        for (int i = 0; i < item_list.length(); i++) {
                                            JSONObject item_temp = item_list.getJSONObject(i);
                                            int parts = item_temp.getInt("parts");
                                            if(parts == 0){
                                                heads.add(new MyItem((parts * 100) + item_temp.getInt("type")));
                                            }
                                            else if(parts == 1){
                                                bodies.add(new MyItem((parts * 100) + item_temp.getInt("type")));
                                            }
                                            else if(parts == 2){
                                                weapons.add(new MyItem((parts * 100) + item_temp.getInt("type")));
                                            }
                                        }
                                        myInfo = new MyInfo(temp.getString("name"),
                                                temp.getInt("gold"),
                                                temp.getInt("topscore"),
                                                heads, bodies, weapons);
                                        m.popMain();
                                    }
                                    else if (result.equals("ID FAIL")){
                                        popNotice("존재하지 않는 ID 입니다", m);
                                    }
                                    else if (result.equals("PWD FAIL")){
                                        popNotice("비밀번호가 틀렸습니다", m);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(m, "Request Error (signincatch)", Toast.LENGTH_LONG).show();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(m, "Request Error", Toast.LENGTH_LONG).show();
                            }
                        });
                        stringRequest.add(myReq);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = idEt.getText().toString().trim();
                final String pwd = pwdEt.getText().toString().trim();
                RequestQueue stringRequest = Volley.newRequestQueue(m);
                String temp = "http://jeho.dothome.co.kr/myDir/running/sign_up.php";
                StringRequest myReq = new StringRequest(Request.Method.POST,
                        temp,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject data = new JSONObject(response);
                                    String result = data.getString("result");
                                    if(result.equals("OK")) {
                                        popNotice("ID가 생성되었습니다", m);
                                    }
                                    else if(result.equals("FAIL")){
                                        popNotice("이미 존재하는 ID 입니다", m);
                                    }
                                    else if(result.equals("ERROR")){
                                        Toast.makeText(m, "Request Error (signuperror)", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(m, "Request Error (signupcatch)", Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(m, "Request Error", Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("id", id);
                        params.put("pwd", pwd);
                        return params;
                    }};
                stringRequest.add(myReq);
            }
        });

        return v;
    }
}
