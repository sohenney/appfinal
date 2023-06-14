package com.trashgo.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.trashgo.app.NoticeUpdateActivity;
import com.trashgo.app.R;

/**
 * 생성 - pkdgood
 */
public class NoticeUpdateFragment extends Fragment {

    Button mbtn_url;
    NoticeUpdateActivity noticeUpdateActivity;

    public NoticeUpdateFragment() {
        // 필수 생성자
    }

    public static NoticeUpdateFragment newInstance() {
        return new NoticeUpdateFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // NoticeUpdateActivity와 연결
        noticeUpdateActivity = (NoticeUpdateActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 레이아웃 inflate
        View view = inflater.inflate(R.layout.fragment_notice_update, container, false);

        // 'btnupdate3' 버튼 설정
        mbtn_url = view.findViewById(R.id.btnupdate3);

        // 'btnupdate3' 버튼 클릭 이벤트 설정
        mbtn_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 웹 페이지로 이동하는 Intent 생성
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://me.go.kr/home/web/board/read.do?pagerOffset=0&maxPageItems=10&maxIndexPages=10&searchKey=&searchValue=&menuId=10181&orgCd=&boardId=1398800&boardMasterId=54&boardCategoryId=&decorator="));
                startActivity(urlintent);
            }
        });

        // 'btnnotice1' 버튼 설정
        Button btnNotice1 = view.findViewById(R.id.btnnotice1);
        btnNotice1.setOnClickListener(v -> noticeUpdateActivity.notice1Clicked());

        // 'btnnotice2' 버튼 설정
        Button btnNotice2 = view.findViewById(R.id.btnnotice2);
        btnNotice2.setOnClickListener(v -> noticeUpdateActivity.notice2Clicked());

        // 'btnupdate1' 버튼 설정
        Button btnUpdate1 = view.findViewById(R.id.btnupdate1);
        btnUpdate1.setOnClickListener(v -> noticeUpdateActivity.update1Clicked());

        // 'btnupdate2' 버튼 설정
        Button btnUpdate2 = view.findViewById(R.id.btnupdate2);
        btnUpdate2.setOnClickListener(v -> noticeUpdateActivity.update2Clicked());

        return view;
    }
}
