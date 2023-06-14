package com.trashgo.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/*by dotom*/
public class CommunityRecyclerViewAdapter extends RecyclerView.Adapter<CommunityRecyclerViewAdapter.ViewHolder> {
    private ArrayList<CommunityRecyclerItem> mList = new ArrayList<>();
    public CommunityRecyclerViewAdapter(ArrayList<CommunityRecyclerItem> m){
        this.mList = m;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_community, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_title.setText(mList.get(position).getTitle());
        holder.item_content.setText(mList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView item_title;
        private TextView item_content;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            item_title = (TextView)itemView.findViewById(R.id.title);
            item_content = (TextView)itemView.findViewById(R.id.content);
        }
    }

}