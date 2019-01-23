package com.mdgiitr.karthik.cognizance19.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.VerticalItemsModel;

import java.util.ArrayList;
import java.util.List;

public class SpotlightsVerticalRVAdapter extends RecyclerView.Adapter<SpotlightsVerticalRVAdapter.SimpleViewHolder> {

    private final Context mContext;
    private static RecyclerView horizontalRV;
    public static List<VerticalItemsModel> mData;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        private SpotlightsHorizontalRVAdapter horizontalAdapter;

        public SimpleViewHolder(View view) {
            super(view);
            Context context = itemView.getContext();
            title = (TextView) view.findViewById(R.id.vertical_rv_textview);
            horizontalRV = (RecyclerView) itemView.findViewById(R.id.horizontal_spotlight_RV);
            horizontalRV.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            horizontalAdapter = new SpotlightsHorizontalRVAdapter();
            horizontalRV.setAdapter(horizontalAdapter);
        }
    }

    public SpotlightsVerticalRVAdapter(Context context, List<VerticalItemsModel> data){
        mContext = context;
        if(data != null){
            mData = new ArrayList<>(data);
        }
        else {
            mData = new ArrayList<>();
        }
    }
    @NonNull
    @Override
    public SpotlightsVerticalRVAdapter.SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.spotlight_vertical_rv_items, viewGroup, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpotlightsVerticalRVAdapter.SimpleViewHolder simpleViewHolder, final int i) {
        simpleViewHolder.title.setText(mData.get(i).getName());
        simpleViewHolder.horizontalAdapter.setData(mData.get(i).getTags(), mData.get(i).getData1(), mData.get(i).getData2()); // List of Strings
        simpleViewHolder.horizontalAdapter.setRowIndex(i);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
