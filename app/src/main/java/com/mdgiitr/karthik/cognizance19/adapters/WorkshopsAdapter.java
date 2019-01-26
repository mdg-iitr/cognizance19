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
import com.mdgiitr.karthik.cognizance19.models.WorkshopsModel;

import java.util.List;

public class WorkshopsAdapter extends RecyclerView.Adapter<WorkshopsAdapter.WorkshopsViewHolder> {
    Context context;
    List<WorkshopsModel> list;

    public WorkshopsAdapter(Context context, List<WorkshopsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public WorkshopsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_workshops, parent, false);
        return new WorkshopsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkshopsViewHolder holder, int position) {
        WorkshopsModel model = list.get(position);
//        holder.image.setImageResource(getImageFromGlide(model.getImageURL()));
        holder.workshopImage.setImageResource(R.drawable.home_menu_gray_card);
        holder.name.setText(model.getWorkshopName());
        holder.category.setText(model.getWorkshopCategory());
        holder.followers.setText(model.getWorkshopFollowers());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WorkshopsViewHolder extends RecyclerView.ViewHolder{
        public ImageView workshopImage;
        public TextView name, category, followers;

        public WorkshopsViewHolder(@NonNull View itemView) {
            super(itemView);
            workshopImage = itemView.findViewById(R.id.workshops_image);
            name = itemView.findViewById(R.id.workshops_name);
            category = itemView.findViewById(R.id.workshops_catagory);
            followers = itemView.findViewById(R.id.workshops_followers);
        }
    }
}
