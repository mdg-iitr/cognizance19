package com.mdgiitr.karthik.cognizance19.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.SpotlightGuestModel;
import com.mdgiitr.karthik.cognizance19.models.SpotlightModel;

import java.util.List;

public class SpotlightAdapter extends RecyclerView.Adapter<SpotlightAdapter.SpotlightViewHolder> {
    Context context;
    List<SpotlightModel> list;

    public SpotlightAdapter(Context context, List<SpotlightModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SpotlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_spotlight, parent, false);

        return new SpotlightViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpotlightViewHolder holder, int i) {
        SpotlightModel model = list.get(i);
        holder.category.setText(model.getCategory());
        List<SpotlightGuestModel> guestList = model.getGuestList();
        SpotlightGuestAdapter adapter = new SpotlightGuestAdapter(guestList);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SpotlightViewHolder extends RecyclerView.ViewHolder {
        TextView category;
        RecyclerView recyclerView;

        public SpotlightViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.spotlight_category);
            recyclerView = itemView.findViewById(R.id.spotlight_category_recyclerview);
        }
    }
}
