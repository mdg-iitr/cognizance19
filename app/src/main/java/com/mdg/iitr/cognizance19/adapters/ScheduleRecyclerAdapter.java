package com.mdg.iitr.cognizance19.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.models.ScheduleEventModel;

import java.util.List;

public class ScheduleRecyclerAdapter extends RecyclerView.Adapter<ScheduleRecyclerAdapter.ScheduleViewHolder> {

    List<ScheduleEventModel> list;

    public ScheduleRecyclerAdapter(List<ScheduleEventModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ScheduleRecyclerAdapter.ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_schedule, parent, false);

        return new ScheduleRecyclerAdapter.ScheduleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleRecyclerAdapter.ScheduleViewHolder holder, int position) {
        ScheduleEventModel scheduleEventModel = list.get(position);

        holder.name.setText(scheduleEventModel.name);
        holder.time.setText(scheduleEventModel.time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView time;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.eventName);
            time = itemView.findViewById(R.id.eventTime);
        }
    }


}
