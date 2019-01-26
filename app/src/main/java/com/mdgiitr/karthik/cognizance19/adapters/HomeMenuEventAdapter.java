package com.mdgiitr.karthik.cognizance19.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.HomeMenuEventModel;

import java.util.List;

import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class HomeMenuEventAdapter extends RecyclerView.Adapter<HomeMenuEventAdapter.HomeMenuEventViewHolder> {
    Context context;
    List<HomeMenuEventModel> myList;

    public HomeMenuEventAdapter(Context context, List<HomeMenuEventModel> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public HomeMenuEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_home_menu_event, parent, false);

        return new HomeMenuEventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMenuEventViewHolder holder, int position) {
        HomeMenuEventModel model = myList.get(position);

        // holder.eventPic.setImage(getFromGlide(model.getImageURL()))
        holder.eventPic.setImageResource(R.drawable.home_menu_gray_card);
        holder.eventName.setText(model.getEventName());
        holder.eventCategory.setText(model.getEventCategory());
        holder.eventFollower.setText(model.getEventFollowers());
        holder.eventCard.setOnClickListener(v -> {
            navController.navigate(R.id.action_homeMenuFragment_to_speceficEventFragment);
        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class HomeMenuEventViewHolder extends RecyclerView.ViewHolder {
        public ImageView eventPic;
        public TextView eventName, eventCategory, eventFollower;
        public CardView eventCard;

        public HomeMenuEventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventPic = itemView.findViewById(R.id.home_menu_event_image);
            eventName = itemView.findViewById(R.id.home_menu_event_name);
            eventCategory = itemView.findViewById(R.id.home_menu_event_catagory);
            eventFollower = itemView.findViewById(R.id.home_menu_event_followers);
            eventCard = itemView.findViewById(R.id.event_card);
        }
    }
}
