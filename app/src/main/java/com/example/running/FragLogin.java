package com.example.running;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FragLogin extends BaseFragment {
    EditText idEt;
    EditText pwdEt;
    ImageView signInBtn;
    ImageView signUpBtn;
    MyInfo myInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_login, container, false);
        final MainActivity m = (MainActivity)getActivity();

        idEt = v.findViewById(R.id.login_idEt);
        pwdEt = v.findViewById(R.id.login_pwdEt);
        signInBtn = v.findViewById(R.id.login_signInBtn);
        signUpBtn = v.findViewById(R.id.login_signUpBtn);
        Glide.with(m).load(R.drawable.jumpbutton).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(signInBtn);
        Glide.with(m).load(R.drawable.jumpbutton).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(signUpBtn);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idEt.getText().toString().trim();
                String pwd = pwdEt.getText().toString().trim();
                RequestQueue stringRequest = Volley.newRequestQueue(m);
                String temp = "http://jeho.dothome.co.kr/myDir/running/sign_in.php?id=" + id + "&pwd=" + pwd;
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
                                        //TODO myInfo = new MyInfo();
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
