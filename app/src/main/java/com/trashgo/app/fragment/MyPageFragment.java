package com.trashgo.app.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trashgo.app.R;
import com.trashgo.app.activity_login;
import com.trashgo.app.activity_mypage;
import com.trashgo.app.activity_noticeupdate;
import com.trashgo.app.activity_notification;
import com.trashgo.app.activity_profile;
import com.trashgo.app.activity_withdrawal;

/**
 * 생성 - pkdgood
 */
public class MyPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    TextView txtlogin;
    TextView txtuserinform;
    TextView notification;
    TextView noticeupdate;
    TextView withdrawal;

    public MyPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyPageFragment newInstance(String param1, String param2) {
        MyPageFragment fragment = new MyPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//        //로그인 및 회원가입 이동 textview
//        txtlogin = findViewById(R.id.txtlogin);
//
//        //textview 클릭시, 로그인 및 회원가입 페이지로 이동
//        txtlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity_mypage.this, activity_login.class);
//                startActivity(intent);
//            }
//        });
//
//        //회원정보 이동 textview
//        txtuserinform = findViewById(R.id.txtuserinform);
//
//        //textview 클릭시, 회원정보 페이지로 이동
//        txtuserinform.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity_mypage.this, activity_profile.class);
//                startActivity(intent);
//            }
//        });
//
//        //알림설정 이동 textview
//        notification = findViewById(R.id.notification);
//
//        //textview 클릭시, 알림설정 페이지로 이동
//        notification.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity_mypage.this, activity_notification.class);
//                startActivity(intent);
//            }
//        });
//
//        //업데이트 및 공지사항 이동 textview
//        noticeupdate = findViewById(R.id.noticeupdate);
//
//        //textview 클릭시, 업데이트 및 공지사항 페이지로 이동
//        noticeupdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity_mypage.this, activity_noticeupdate.class);
//                startActivity(intent);
//            }
//        });
//
//        //회원탈퇴 이동 textview
//        withdrawal = findViewById(R.id.withdrawal);
//
//        //textview 클릭시, 업데이트 및 공지사항 페이지로 이동
//        withdrawal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity_mypage.this, activity_withdrawal.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_page, container, false);
    }
}