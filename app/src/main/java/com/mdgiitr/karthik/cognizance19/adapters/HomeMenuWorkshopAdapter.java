package com.mdgiitr.karthik.cognizance19.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.HomeMenuEventModel;

import java.util.List;

public class HomeMenuWorkshopAdapter extends RecyclerView.Adapter<HomeMenuWorkshopAdapter.HomeMenuWorkshopViewHolder> {
    Context context;
    List<HomeMenuEventModel> list;

    public HomeMenuWorkshopAdapter(Context context, List<HomeMenuEventModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeMenuWorkshopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home_menu_workshop, parent, false);
        return new HomeMenuWorkshopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMenuWorkshopViewHolder holder, int position) {
        HomeMenuEventModel model = list.get(position);

//        holder.eventPic.setImageBitmap(getImageFromGlide(model.getEventPicURl()));
        holder.eventPic.setImageResource(R.drawable.home_menu_gray_card);
        holder.eventName.setText(model.getEventName());
        holder.eventCategory.setText(model.getEventCategory());
        holder.eventFollower.setText(model.getEventFollowers());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeMenuWorkshopViewHolder extends RecyclerView.ViewHolder{
        public ImageView eventPic;
        public TextView eventName, eventCategory, eventFollower;
        public HomeMenuWorkshopViewHolder(@NonNull View itemView) {
            super(itemView);
            eventPic = itemView.findViewById(R.id.home_menu_workshop_image);
            eventName = itemView.findViewById(R.id.home_menu_workshop_name);
            eventCategory = itemView.findViewById(R.id.home_menu_workshop_catagory);
            eventFollower = itemView.findViewById(R.id.home_menu_workshop_followers);
        }
    }
}
