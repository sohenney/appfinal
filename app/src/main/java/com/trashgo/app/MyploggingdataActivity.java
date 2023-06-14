package com.trashgo.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.ArrayList;
/*by dotom*/
public class MyploggingdataActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<ploggingDataRecyclerViewItem> mList;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myploggingdata);

        firstInit();

//        for(int i=0;i<5;i++){
//            addItem("iconName", "dotom" + i);
//        }

        addItem("쓰레기", "위경도\n36.6259, 127.4543");

        mRecyclerViewAdapter = new RecyclerViewAdapter(mList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void firstInit(){
        mRecyclerView = (RecyclerView) findViewById(R.id.pdRecyclerView);
        mList = new ArrayList<>();
    }

    public void addItem(String imgName, String mainText){
        ploggingDataRecyclerViewItem item = new ploggingDataRecyclerViewItem();

        item.setImgName(imgName);
        item.setDate(mainText);

        mList.add(item);
    }
}