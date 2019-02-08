package com.mdgiitr.karthik.cognizance19.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.RegEventsModel;

import java.util.ArrayList;
import java.util.List;

public class RegEventsAdapter extends RecyclerView.Adapter<RegEventsAdapter.RegEventsViewHolder> {

    Context context;
    private List<RegEventsModel> list;

    public RegEventsAdapter(Context context, List<RegEventsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RegEventsAdapter.RegEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reg_events, parent, false);
        return new RegEventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegEventsAdapter.RegEventsViewHolder holder, final int i) {
        RegEventsModel model = list.get(i);
        holder.eventName.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RegEventsViewHolder extends RecyclerView.ViewHolder {
        TextView eventName;
        ImageView moreButton;

        public RegEventsViewHolder(View view) {
            super(view);
            eventName = view.findViewById(R.id.reg_event_name);
            moreButton = view.findViewById(R.id.reg_events_more_button);
        }
    }
}
