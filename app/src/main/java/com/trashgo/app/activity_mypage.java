package com.trashgo.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// sohenney activity_mypage.java 추가
public class activity_mypage extends AppCompatActivity {
    TextView txtlogin;
    TextView txtuserinform;
    TextView notification;
    TextView noticeupdate;
    TextView withdrawal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        //로그인 및 회원가입 이동 textview
        txtlogin = findViewById(R.id.txtlogin);

        //textview 클릭시, 로그인 및 회원가입 페이지로 이동
        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_mypage.this, activity_login.class);
                startActivity(intent);
            }
        });

        //회원정보 이동 textview
        txtuserinform = findViewById(R.id.txtuserinform);

        //textview 클릭시, 회원정보 페이지로 이동
        txtuserinform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_mypage.this, activity_profile.class);
                startActivity(intent);
            }
        });

        //알림설정 이동 textview
        notification = findViewById(R.id.notification);

        //textview 클릭시, 알림설정 페이지로 이동
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_mypage.this, activity_notification.class);
                startActivity(intent);
            }
        });

        //업데이트 및 공지사항 이동 textview
        noticeupdate = findViewById(R.id.noticeupdate);

        //textview 클릭시, 업데이트 및 공지사항 페이지로 이동
        noticeupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_mypage.this, NoticeUpdateActivity.class);
                startActivity(intent);
            }
        });

        //회원탈퇴 이동 textview
        withdrawal = findViewById(R.id.withdrawal);

        //textview 클릭시, 업데이트 및 공지사항 페이지로 이동
        withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_mypage.this, activity_withdrawal.class);
                startActivity(intent);
            }
        });
    }
}