package com.mdgiitr.karthik.cognizance19.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.RegEventsModel;

import java.util.ArrayList;
import java.util.List;

public class RegEventsTabFragmentRVAdapter extends RecyclerView.Adapter<RegEventsTabFragmentRVAdapter.SimpleViewHolder> {

    Context mContext;
    private List<RegEventsModel> mData;

    public RegEventsTabFragmentRVAdapter(Context context, List<RegEventsModel> data) {
        mContext = context;
        if (data != null) {
            mData = new ArrayList<>(data);
        } else {
            mData = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public RegEventsTabFragmentRVAdapter.SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.reg_events_tab_list, viewGroup, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegEventsTabFragmentRVAdapter.SimpleViewHolder simpleViewHolder, final int i) {
        simpleViewHolder.title.setText(mData.get(i).getString());
        simpleViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Working Fine!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        Button button;

        public SimpleViewHolder(View view) {
            super(view);
            Context context = itemView.getContext();
            title = (TextView) view.findViewById(R.id.reg_events_tab_RV_text);
            button = (Button) view.findViewById(R.id.reg_events_tab_RV_button);
        }
    }
}
