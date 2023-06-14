package com.trashgo.app.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.trashgo.app.CommunityRecyclerItem;
import com.trashgo.app.CommunityRecyclerViewAdapter;
import com.trashgo.app.MainActivity;
import com.trashgo.app.MyCommunityRecyclerViewAdapter;
import com.trashgo.app.R;
import com.trashgo.app.placeholder.PlaceholderContent;

import java.sql.Array;
import java.util.ArrayList;

/**
 * 생성 - pkdgood
 */
/** 커뮤니티 By dotom*/
public class CommunityFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private RecyclerView mRecyclerView;
    static private ArrayList<CommunityRecyclerItem> mList= new ArrayList<>();
    private MyCommunityRecyclerViewAdapter mRecyclerViewAdapter;
    MainActivity mainActivity;
    Button writebutton;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CommunityFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CommunityFragment newInstance(int columnCount) {
        CommunityFragment fragment = new CommunityFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }


    }

    /**
     * by dotom
     */
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    /**
     * by dotom
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_community_list, container, false);

        // Set the adapter




        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        mRecyclerViewAdapter = new MyCommunityRecyclerViewAdapter(mList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        writebutton = view.findViewById(R.id.writebutton);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        writebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mainActivity.fragmentChange(1);
            }
        });
        return view;
    }
    /** By dotom*/
    public void addItem(String title, String content){
        CommunityRecyclerItem item = new CommunityRecyclerItem();

        item.setTitle(title);
        item.setContent(content);

        mList.add(item);
    }

}