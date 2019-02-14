package com.mdgiitr.karthik.cognizance19.adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.Departmental;
import com.mdgiitr.karthik.cognizance19.view.EventDepartmentalSpecificFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DepartmentalAdapter extends RecyclerView.Adapter<DepartmentalAdapter.DepartmentalViewHolder> {

    List<Departmental> list;
    Context context;
    FragmentManager manager;
    int screenWidth = 0;

    public DepartmentalAdapter(Context context, List<Departmental> list, int screenWidth, FragmentManager manager) {
        this.context = context;
        this.list = list;
        this.screenWidth = screenWidth;
        this.manager = manager;
    }

    @NonNull
    @Override
    public DepartmentalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_departmental, parent, false);

        return new DepartmentalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentalViewHolder holder, int position) {
        Departmental departmentalModel = list.get(position);
        holder.deptIcon.getLayoutParams().height = (screenWidth / 2) - 100;
        holder.deptIcon.getLayoutParams().width = (screenWidth / 2) - 100;
        holder.deptNameView.setText(departmentalModel.getName());
        holder.deptIcon.requestLayout();
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("deptName",departmentalModel.getName());
                bundle.putSerializable("eventList", (Serializable) departmentalModel.getEvents());
                EventDepartmentalSpecificFragment departmentalSpecificFragment = new EventDepartmentalSpecificFragment();
                departmentalSpecificFragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.base, departmentalSpecificFragment)
                                            .addToBackStack("departmental")
                                            .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DepartmentalViewHolder extends RecyclerView.ViewHolder {
        public TextView deptNameView;
        public CircleImageView deptIcon;
        public LinearLayout linearLayout;

        public DepartmentalViewHolder(@NonNull View itemView) {
            super(itemView);
            deptNameView = itemView.findViewById(R.id.dept_nameTextView);
            deptIcon = itemView.findViewById(R.id.department_image);
            linearLayout = itemView.findViewById(R.id.departmental_linear_layout);
        }
    }

}
