package com.trashgo.app;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trashgo.app.placeholder.PlaceholderContent.PlaceholderItem;
import com.trashgo.app.databinding.FragmentCommunityBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCommunityRecyclerViewAdapter extends RecyclerView.Adapter<MyCommunityRecyclerViewAdapter.ViewHolder> {

    private final List<CommunityRecyclerItem> mValues;

    public MyCommunityRecyclerViewAdapter(List<CommunityRecyclerItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentCommunityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getTitle());
        holder.mContentView.setText(mValues.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public CommunityRecyclerItem mItem;

        public ViewHolder(FragmentCommunityBinding binding) {
            super(binding.getRoot());
            mIdView = binding.title;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}