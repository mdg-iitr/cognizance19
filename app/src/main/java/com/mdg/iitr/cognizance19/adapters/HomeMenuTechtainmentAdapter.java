package com.mdg.iitr.cognizance19.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mdg.iitr.cognizance19.R;

import java.util.List;

public class HomeMenuTechtainmentAdapter extends RecyclerView.Adapter<HomeMenuTechtainmentAdapter.HomeMenuTechtainmentViewHolder> {
    Context context;
    List<String> urlList;

    public HomeMenuTechtainmentAdapter(Context context, List<String> urlList) {
        this.context = context;
        this.urlList = urlList;
    }

    @NonNull
    @Override
    public HomeMenuTechtainmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_home_menu_techtainment, parent, false);

        return new HomeMenuTechtainmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMenuTechtainmentViewHolder holder, int position) {
        String url = urlList.get(position);
        // holder.image.setImage(Retrieve image using glide for the given url).
        holder.image.setImageResource(R.drawable.home_menu_white_card); //Dummy data.
        holder.image.setOnClickListener(v -> {

        });
    }


    @Override
    public int getItemCount() {
        return urlList.size();
    }

    public class HomeMenuTechtainmentViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public HomeMenuTechtainmentViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.home_menu_techtainment_image);
        }
    }
}
