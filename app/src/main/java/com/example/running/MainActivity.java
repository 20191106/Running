package com.example.running;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

//<Login>
//보안
//        동시로그인
//<Main>
//myInfo 불러오기
//        세팅팝업
//<Item <-> Store>
//        view 구현 (1)
//        파츠별 이미지
//<Game>
//공격, 비행 기능 (2)
//<전체>
//UI재조정

public class MainActivity extends AppCompatActivity {
    FragGame fragGame = new FragGame();
    FragLogin fragLogin = new FragLogin();
    FragMain fragMain = new FragMain();
    FragItem fragItem = new FragItem();
    FragStore fragStore = new FragStore();

    int curScr = 0;
    final int FRAG_LOGIN = 0;
    final int FRAG_MAIN = 1;
    final int FRAG_GAME = 2;
    final int FRAG_ITEM = 3;
    final int FRAG_STORE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        hideNavigationBar();
        setContentView(R.layout.activity_main);

        popLogin();
    }

    public void popGame() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        curScr = FRAG_GAME;
        ft.replace(R.id.mainLayout, fragGame);
        ft.commit();
    }

    public void popLogin() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        curScr = FRAG_LOGIN;
        ft.replace(R.id.mainLayout, fragLogin);
        ft.commit();
    }

    public void popMain() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        curScr = FRAG_MAIN;
        ft.replace(R.id.mainLayout, fragMain);
        ft.commit();
    }

    public void popItem() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        curScr = FRAG_ITEM;
        ft.replace(R.id.mainLayout, fragItem);
        ft.commit();
    }

    public void popStore() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        curScr = FRAG_STORE;
        ft.replace(R.id.mainLayout, fragStore);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        switch (curScr){
            case FRAG_LOGIN:
            case FRAG_MAIN:
                finish();
                break;
            case FRAG_GAME:
            case FRAG_STORE:
            case FRAG_ITEM:
                popMain();
                break;
        }
    }

    private void hideNavigationBar() {
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
        } else {
        }
        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }
}
