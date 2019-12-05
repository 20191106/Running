package com.example.running.Model;

import android.net.Uri;

import com.example.running.R;

public class MyItem {
    public int type;
    public String name;
    public String detail;
    public int imageId;

    // type = parts(0) + type(00)

    public MyItem(int type) {
        this.type = type;

        switch (type){
            case 1:
                name = "모자1";
                detail = "골드 + 1";
                imageId = R.drawable.headtype1;
                break;
            case 2:
                name = "모자2";
                detail = "골드 + 2";
                imageId = R.drawable.headtype1;
                break;
            case 101:
                name = "옷1";
                detail = "체력 + 1";
                imageId = R.drawable.bodytype1;
                break;
            case 201:
                name = "무기1";
                detail = "공격력 + 1";
                imageId = R.drawable.weapontype1;
                break;
        }
    }
}
