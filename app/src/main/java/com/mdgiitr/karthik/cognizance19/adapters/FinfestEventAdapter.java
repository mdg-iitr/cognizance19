package com.mdgiitr.karthik.cognizance19.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.FinfestEventModel;

import java.util.List;

public class FinfestEventAdapter extends RecyclerView.Adapter<FinfestEventAdapter.FinfestEventViewHolder> {
    List<FinfestEventModel> eventList;

    public FinfestEventAdapter(List<FinfestEventModel> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public FinfestEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_finfest_event, parent, false);

        return new FinfestEventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FinfestEventViewHolder holder, int position) {
        FinfestEventModel model = eventList.get(position);

        // holder.eventPic.setImage(getFromGlide(model.getImageURL()))
        holder.eventPic.setImageResource(R.drawable.home_menu_gray_card);
        holder.eventName.setText(model.getEventName());
        holder.eventCategory.setText(model.getEventCategory());
        holder.eventFollower.setText(model.getEventFollowers());

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class FinfestEventViewHolder extends RecyclerView.ViewHolder {
        public ImageView eventPic;
        public TextView eventName, eventCategory, eventFollower;

        public FinfestEventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventPic = itemView.findViewById(R.id.finfest_event_image);
            eventName = itemView.findViewById(R.id.finfest_event_name);
            eventCategory = itemView.findViewById(R.id.finfest_event_catagory);
            eventFollower = itemView.findViewById(R.id.finfest_event_followers);
        }
    }
}
