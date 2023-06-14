package com.trashgo.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import com.trashgo.app.MainActivity;
import com.trashgo.app.R;
import com.trashgo.app.aiActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComuWriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * by dotom
 */
public class ComuWriteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button uploadbutton;
    EditText title;
    MultiAutoCompleteTextView content;
    MainActivity mainActivity;
    static CommunityFragment item = new CommunityFragment();
    public ComuWriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComuWriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComuWriteFragment newInstance(String param1, String param2) {
        ComuWriteFragment fragment = new ComuWriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comuwrite, container, false);
        uploadbutton = view.findViewById(R.id.uploadbutton);
        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content);
        uploadbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                item.addItem(title.getText().toString(), content.getText().toString());
                title.setText("");
                content.setText("");

                mainActivity.fragmentChange(2);


            }



        });

        return view;
    }
}