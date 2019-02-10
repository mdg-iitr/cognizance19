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
import com.mdgiitr.karthik.cognizance19.models.Centerstage;
import com.mdgiitr.karthik.cognizance19.models.Event;

import java.util.List;

public class CenterstageAdapter extends RecyclerView.Adapter<CenterstageAdapter.CenterstageViewHolder> {
    List<Centerstage> list;
    Context context;

    public CenterstageAdapter(Context context, List<Centerstage> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CenterstageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_centerstage, parent, false);

        return new CenterstageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CenterstageViewHolder holder, int position) {
        Centerstage centerstageModel = list.get(position);

        holder.category.setText(centerstageModel.getName());
        holder.recyclerView.setAdapter(new CenterstageEventAdapter(context,centerstageModel.getEvents()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CenterstageViewHolder extends RecyclerView.ViewHolder{
        public TextView category;
        public RecyclerView recyclerView;

        public CenterstageViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.centerstage_category);
            recyclerView = itemView.findViewById(R.id.centerstage_category_recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        }
    }
}