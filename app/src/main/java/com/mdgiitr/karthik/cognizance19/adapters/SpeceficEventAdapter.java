package com.mdgiitr.karthik.cognizance19.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mdgiitr.karthik.cognizance19.R;

import java.util.List;

public class SpeceficEventAdapter extends RecyclerView.Adapter<SpeceficEventAdapter.SpeceficEventViewHolder> {
    Context context;
    List<String> imageURLlist;

    public SpeceficEventAdapter(Context context, List<String> imageURLlist) {
        this.context = context;
        this.imageURLlist = imageURLlist;
    }

    @NonNull
    @Override
    public SpeceficEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_specefic_event_image, parent, false);
        return new SpeceficEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeceficEventViewHolder holder, int i) {
        String url = imageURLlist.get(i);
        holder.specificEventImage.setImageResource(R.drawable.gray_round_card);
    }

    @Override
    public int getItemCount() {
        return imageURLlist.size();
    }

    public class SpeceficEventViewHolder extends RecyclerView.ViewHolder{
        public ImageView specificEventImage;
        public SpeceficEventViewHolder(@NonNull View itemView) {
            super(itemView);
            specificEventImage = itemView.findViewById(R.id.specific_event_image);
        }
    }
}
