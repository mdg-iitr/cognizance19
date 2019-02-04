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
import com.mdgiitr.karthik.cognizance19.models.FinfestEventModel;
import com.mdgiitr.karthik.cognizance19.models.FinfestModel;

import java.util.List;

public class FinfestAdapter extends RecyclerView.Adapter<FinfestAdapter.FinfestViewHolder> {
    List<FinfestModel> list;
    Context context;

    public FinfestAdapter(Context context, List<FinfestModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FinfestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_finfest, parent, false);

        return new FinfestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FinfestViewHolder holder, int position) {
        FinfestModel finfestModel = list.get(position);

        holder.category.setText(finfestModel.getCategory());
        List<FinfestEventModel> finfestEventModelList = finfestModel.getEventList();
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setAdapter(new FinfestEventAdapter(finfestEventModelList));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FinfestViewHolder extends RecyclerView.ViewHolder{
        public TextView category;
        public RecyclerView recyclerView;

        public FinfestViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.finfest_category);
            recyclerView = itemView.findViewById(R.id.finfest_category_recyclerview);
        }
    }
}
