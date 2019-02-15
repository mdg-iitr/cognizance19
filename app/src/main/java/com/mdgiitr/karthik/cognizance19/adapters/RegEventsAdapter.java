package com.mdgiitr.karthik.cognizance19.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.GeneralResponse;
import com.mdgiitr.karthik.cognizance19.models.RegEventsModel;
import com.mdgiitr.karthik.cognizance19.models.TeamResponse;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import java.util.List;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegEventsAdapter extends RecyclerView.Adapter<RegEventsAdapter.RegEventsViewHolder> {

    Context context;
    private List<RegEventsModel> list;
    private ApiClient apiClient;
    private PreferenceHelper preferenceHelper;

    public RegEventsAdapter(Context activityContext, Context context, List<RegEventsModel> list) {
        this.context = context;
        this.list = list;
        apiClient = new ApiClient();
        preferenceHelper = new PreferenceHelper(activityContext);
    }

    @NonNull
    @Override
    public RegEventsAdapter.RegEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reg_events, parent, false);
        return new RegEventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegEventsViewHolder holder, int position) {
        RegEventsModel model = list.get(position);
        holder.eventName.setText(model.getName());
        PopupMenu popupMenu = new PopupMenu(context, holder.moreButton);
        popupMenu.getMenuInflater().inflate(R.menu.reg_event_menu, popupMenu.getMenu());
        holder.moreButton.setOnClickListener((View v) -> popupMenu.show());
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.unregister) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Unregister");
                alert.setMessage("Unregister for " + model.getName() + "?");
                alert.setPositiveButton("Unregister", (dialog, which) -> {
                    apiClient.unregister(preferenceHelper.getToken(),Integer.toString(model.getId()))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<GeneralResponse>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(GeneralResponse generalResponse) {
                                    Toast.makeText(context,generalResponse.message,Toast.LENGTH_LONG).show();
                                    if(generalResponse.message.equals("Successfully Unregistered.")){
                                        list.remove(position);
                                        notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                });
                alert.setNegativeButton("Close", (dialog, which) -> {
                    // close dialog
                    dialog.cancel();
                });
                alert.show();




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
