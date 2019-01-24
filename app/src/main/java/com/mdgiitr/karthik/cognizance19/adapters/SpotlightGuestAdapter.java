package com.mdgiitr.karthik.cognizance19.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.SpotlightGuestModel;

import java.util.List;

public class SpotlightGuestAdapter extends RecyclerView.Adapter<SpotlightGuestAdapter.SpotlightGuestViewHolder> {
    List<SpotlightGuestModel> guestList;

    public SpotlightGuestAdapter(List<SpotlightGuestModel> guestList) {
        this.guestList = guestList;
    }

    @NonNull
    @Override
    public SpotlightGuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_spotlight_guest, parent, false);

        return new SpotlightGuestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpotlightGuestViewHolder holder, int i) {
        SpotlightGuestModel guestModel = guestList.get(i);
//        holder.image.setImageURI(getImagefromGlide(guestModel.getImageURL()));
        holder.image.setImageResource(R.drawable.home_menu_gray_card);
        holder.guestName.setText(guestModel.getGuestName());
        if (guestModel.getGuestDesignation() == null || guestModel.getGuestDesignation().isEmpty()) holder.guestDesignation.setVisibility(View.GONE);
        else holder.guestDesignation.setText(guestModel.getGuestDesignation());
    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }

    public class SpotlightGuestViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView guestName, guestDesignation;

        public SpotlightGuestViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.spotlight_guest_image);
            guestName = itemView.findViewById(R.id.spotlight_guest_name);
            guestDesignation = itemView.findViewById(R.id.spotlight_guest_designation);
        }
    }
}
