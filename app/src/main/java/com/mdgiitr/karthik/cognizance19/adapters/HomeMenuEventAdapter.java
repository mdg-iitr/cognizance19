package com.mdgiitr.karthik.cognizance19.adapters;

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
import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.HomeMenuEventModel;

import java.util.List;

import static com.mdgiitr.karthik.cognizance19.MainActivity.EVENT_FRAG;
import static com.mdgiitr.karthik.cognizance19.MainActivity.bottomNavigationView;
import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class HomeMenuEventAdapter extends RecyclerView.Adapter<HomeMenuEventAdapter.HomeMenuEventViewHolder> {
    Context context;
    List<HomeMenuEventModel> myList;
    int flag;

    public HomeMenuEventAdapter(Context context, List<HomeMenuEventModel> myList, int flag) {
        this.context = context;
        this.myList = myList;
        this.flag = flag;
    }

    @NonNull
    @Override
    public HomeMenuEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if(flag==0) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_home_menu_event, parent, false);
        }
        else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_home_menu_attraction, parent, false);
        }
        return new HomeMenuEventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMenuEventViewHolder holder, int position) {
        HomeMenuEventModel model = myList.get(position);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.home_menu_gray_card)
                .error(R.drawable.home_menu_gray_card);
        Glide.with(context)
                .load(model.getImageURL())
                .apply(options)
                .into(holder.eventPic);
        holder.eventName.setText(model.getEvent());
        holder.eventCard.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            if (model.getEvent().equals("Centerstage")) {
                EVENT_FRAG = 0;
                bottomNavigationView.setSelectedItemId(R.id.events);
            } else if (model.getEvent().equals("Departmental")) {
                EVENT_FRAG = 1;
                bottomNavigationView.setSelectedItemId(R.id.events);
            } else if (model.getEvent().equals("Workshops")) {
                bottomNavigationView.setSelectedItemId(R.id.workshops);
            } else if (model.getEvent().equals("FinFest")) {
                EVENT_FRAG = 0;
                bottomNavigationView.setSelectedItemId(R.id.whatsNew);
            } else if (model.getEvent().equals("Lit.A.F")) {
                EVENT_FRAG = 1;
                bottomNavigationView.setSelectedItemId(R.id.whatsNew);
            }

        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class HomeMenuEventViewHolder extends RecyclerView.ViewHolder {
        public ImageView eventPic;
        public TextView eventName;
        public CardView eventCard;

        public HomeMenuEventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventPic = itemView.findViewById(R.id.home_menu_event_image);
            eventName = itemView.findViewById(R.id.home_menu_event_name);
            eventCard = itemView.findViewById(R.id.event_card);
        }
    }
}
