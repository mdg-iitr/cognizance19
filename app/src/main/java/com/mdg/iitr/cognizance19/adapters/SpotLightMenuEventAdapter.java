package com.mdg.iitr.cognizance19.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.models.HomeMenuEventModel;
import com.mdg.iitr.cognizance19.models.SpotlightEventModel;

import java.util.List;

import static com.mdg.iitr.cognizance19.MainActivity.EVENT_FRAG;
import static com.mdg.iitr.cognizance19.MainActivity.bottomNavigationView;

public class SpotLightMenuEventAdapter extends RecyclerView.Adapter<SpotLightMenuEventAdapter.SpotlightMenuEventViewHolder> {
    Context context;
    List<SpotlightEventModel> myList;

    public SpotLightMenuEventAdapter(Context context, List<SpotlightEventModel> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public SpotlightMenuEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_spotlight_menu, parent, false);
        return new SpotlightMenuEventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpotlightMenuEventViewHolder holder, int position) {
        SpotlightEventModel model = myList.get(position);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.home_menu_gray_card)
                .error(R.drawable.home_menu_gray_card);
        Glide.with(context)
                .load(model.getThumbnail())
                .apply(options)
                .into(holder.eventPic);
        holder.eventName.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class SpotlightMenuEventViewHolder extends RecyclerView.ViewHolder {
        public ImageView eventPic;
        public TextView eventName;
        public CardView eventCard;

        public SpotlightMenuEventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventPic = itemView.findViewById(R.id.spotlight_event_image);
            eventName = itemView.findViewById(R.id.spotlight_event_name);
            eventCard = itemView.findViewById(R.id.event_card);
        }
    }
}
