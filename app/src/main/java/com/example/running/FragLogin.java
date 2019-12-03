package com.example.running;

import android.opengl.ETC1;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FragLogin extends BaseFragment {
    EditText idEt;
    EditText pwdEt;
    Button signInBtn;
    Button signUpBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_login, container, false);
        MainActivity m = (MainActivity)getActivity();

        idEt = v.findViewById(R.id.login_idEt);
        pwdEt = v.findViewById(R.id.login_pwdEt);
        signInBtn = v.findViewById(R.id.login_signInBtn);
        signUpBtn = v.findViewById(R.id.login_signUpBtn);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }
}
