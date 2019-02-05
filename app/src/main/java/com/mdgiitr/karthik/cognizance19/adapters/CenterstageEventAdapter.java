package com.mdgiitr.karthik.cognizance19.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.Event;

import java.util.List;

public class CenterstageEventAdapter extends RecyclerView.Adapter<CenterstageEventAdapter.CenterstageEventViewHolder> {
    List<Event> eventList;

    public CenterstageEventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public CenterstageEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_centerstage_event, parent, false);

        return new CenterstageEventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CenterstageEventViewHolder holder, int position) {
        Event model = eventList.get(position);

        // holder.eventPic.setImage(getFromGlide(model.getImageURL()))
        holder.eventPic.setImageResource(R.drawable.home_menu_gray_card);
        holder.eventName.setText(model.getName());
        holder.eventTagline.setText(model.getTagline());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class CenterstageEventViewHolder extends RecyclerView.ViewHolder {
        public ImageView eventPic;
        public TextView eventName, eventTagline;

        public CenterstageEventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventPic = itemView.findViewById(R.id.centerstage_event_image);
            eventName = itemView.findViewById(R.id.centerstage_event_name);
            eventTagline = itemView.findViewById(R.id.centerstage_event_tagline);
        }
    }
}

