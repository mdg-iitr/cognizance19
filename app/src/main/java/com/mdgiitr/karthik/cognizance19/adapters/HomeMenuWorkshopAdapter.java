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
import com.mdgiitr.karthik.cognizance19.models.EventModel;
import com.mdgiitr.karthik.cognizance19.models.HomeMenuWorkshopsModel;

import java.util.List;

import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class HomeMenuWorkshopAdapter extends RecyclerView.Adapter<HomeMenuWorkshopAdapter.HomeMenuWorkshopViewHolder> {
    private Context context;
    private List<HomeMenuWorkshopsModel> list;

    public HomeMenuWorkshopAdapter(Context context, List<HomeMenuWorkshopsModel> list) {
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
        HomeMenuWorkshopsModel model = list.get(position);

        holder.workshopPic.setImageResource(R.drawable.home_menu_gray_card);
        holder.workshopName.setText(model.getWorkshopName());
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
