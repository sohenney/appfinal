package com.trashgo.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.trashgo.app.fragment.Notice1Fragment;
import com.trashgo.app.fragment.Notice2Fragment;
import com.trashgo.app.fragment.NoticeUpdateFragment;
import com.trashgo.app.fragment.Update1Fragment;
import com.trashgo.app.fragment.Update2Fragment;

// sohenney activity_noticeupdate.java 추가
// pkdgood Fragment 연결 2023-06-09
public class NoticeUpdateActivity extends AppCompatActivity{
    private Fragment noticeUpdateFragment, notice1Fragment, notice2Fragment, update1Fragment, update2Fragment;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_update);

        frameLayout = (FrameLayout) findViewById(R.id.noticeUpdateFrame);

        noticeUpdateFragment = new NoticeUpdateFragment();
        notice1Fragment = new Notice1Fragment();
        notice2Fragment = new Notice2Fragment();
        update1Fragment = new Update1Fragment();
        update2Fragment = new Update2Fragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.noticeUpdateFrame, noticeUpdateFragment).commit();
    }

    public void notice1Clicked() {
        getSupportFragmentManager().beginTransaction().replace(R.id.noticeUpdateFrame, notice1Fragment).commit();
    }
    public void notice2Clicked() {
        getSupportFragmentManager().beginTransaction().replace(R.id.noticeUpdateFrame, notice2Fragment).commit();
    }

    public void update1Clicked() {
        getSupportFragmentManager().beginTransaction().replace(R.id.noticeUpdateFrame, update1Fragment).commit();
    }
    public void update2Clicked() {
        getSupportFragmentManager().beginTransaction().replace(R.id.noticeUpdateFrame, update2Fragment).commit();
    }
}
