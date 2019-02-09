package com.mdgiitr.karthik.cognizance19.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.RegEventsModel;

import java.util.List;

public class RegEventsAdapter extends RecyclerView.Adapter<RegEventsAdapter.RegEventsViewHolder> {

    Context context;
    private List<RegEventsModel> list;
    private PopupMenu popupMenu;

    public RegEventsAdapter(Context context, List<RegEventsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RegEventsAdapter.RegEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reg_events, parent, false);
        return new RegEventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegEventsAdapter.RegEventsViewHolder holder, final int i) {
        RegEventsModel model = list.get(i);
        holder.eventName.setText(model.getName());
        popupMenu = new PopupMenu(context, holder.moreButton);
        popupMenu.getMenuInflater().inflate(R.menu.reg_event_menu, popupMenu.getMenu());
        holder.moreButton.setOnClickListener((View v) -> popupMenu.show());
        popupMenu.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.unregister) {

            } else if (item.getItemId() == R.id.manage_team) {

            } else if (item.getItemId() == R.id.submit_abstract) {

            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RegEventsViewHolder extends RecyclerView.ViewHolder {
        TextView eventName;
        ImageView moreButton;

        public RegEventsViewHolder(View view) {
            super(view);
            eventName = view.findViewById(R.id.reg_event_name);
            moreButton = view.findViewById(R.id.reg_events_more_button);
        }
    }
}
