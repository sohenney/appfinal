package com.trashgo.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CommunityActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<CommunityRecyclerItem> mList;
    private CommunityRecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community);

        firstInit();

        for(int i=0;i<10;i++){
            addItem("Title"+i, "Content"+i);
        }

        mRecyclerViewAdapter = new CommunityRecyclerViewAdapter(mList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
    }

    public void firstInit(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mList = new ArrayList<>();
    }

    public void addItem(String title, String content){
        CommunityRecyclerItem item = new CommunityRecyclerItem();

        item.setTitle(title);
        item.setContent(content);

        mList.add(item);
    }
}