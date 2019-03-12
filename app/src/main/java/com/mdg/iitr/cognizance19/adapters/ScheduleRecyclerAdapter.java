package com.mdg.iitr.cognizance19.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.models.ScheduleEventModel;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import java.util.List;

import static com.mdg.iitr.cognizance19.MainActivity.navController;

public class ScheduleRecyclerAdapter extends RecyclerView.Adapter<ScheduleRecyclerAdapter.ScheduleViewHolder> {

    List<ScheduleEventModel> list;
    Context context;
    PreferenceHelper preferenceHelper;

    public ScheduleRecyclerAdapter(List<ScheduleEventModel> list, Context context) {
        this.list = list;
        this.context = context;
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

        Bundle bundle = new Bundle();
        bundle.putInt("id", scheduleEventModel.eventId);
        preferenceHelper = new PreferenceHelper(context);
        if (preferenceHelper.getLoginStatus()) {
            holder.itemView.setOnClickListener(v -> navController.navigate(R.id.action_scheduleFragment_to_speceficEventFragment, bundle));
        } else {
            Toast.makeText(context, "Please login to continue", Toast.LENGTH_SHORT).show();
        }
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
