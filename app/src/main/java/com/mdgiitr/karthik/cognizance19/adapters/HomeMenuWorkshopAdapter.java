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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.Event;

import java.util.List;

public class HomeMenuWorkshopAdapter extends RecyclerView.Adapter<HomeMenuWorkshopAdapter.HomeMenuWorkshopViewHolder> {
    private Context context;
    private List<Event> list;

    public HomeMenuWorkshopAdapter(Context context, List<Event> list) {
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
        Event model = list.get(position);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.home_menu_gray_card);
        Glide.with(context)
                .load(model.getImageURL())
                .apply(options)
                .into(holder.workshopPic);

        holder.workshopName.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HomeMenuWorkshopViewHolder extends RecyclerView.ViewHolder {
        ImageView workshopPic;
        TextView workshopName, workshopTagline;
        CardView workshopCard;

        HomeMenuWorkshopViewHolder(@NonNull View itemView) {
            super(itemView);
            workshopPic = itemView.findViewById(R.id.home_menu_workshop_image);
            workshopName = itemView.findViewById(R.id.home_menu_workshop_name);
            workshopTagline = itemView.findViewById(R.id.home_menu_workshop_tagline);
            workshopCard = itemView.findViewById(R.id.workshopCard);
        }
    }
}
