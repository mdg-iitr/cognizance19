package com.mdgiitr.karthik.cognizance19.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.TeamMember;
import com.mdgiitr.karthik.cognizance19.models.TeamResponse;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class ManageTeamAdapter extends RecyclerView.Adapter<ManageTeamAdapter.ManageTeamViewHolder> {

    private List<TeamMember> members;
    private int teamLimit = 0;
    private ApiClient apiClient;
    private PreferenceHelper preferenceHelper;
    private Context activityContext;
    private boolean isTeamLeader = false;
    private Context context;
    private Integer teamID, eventID;

    public ManageTeamAdapter(int teamLimit, List<TeamMember> members, boolean isTeamLeader, Integer teamID, Integer eventID, Context activityContext, Context context) {
        this.teamLimit = teamLimit;
        this.members = members;
        apiClient = new ApiClient();
        this.activityContext = activityContext;
        this.isTeamLeader = isTeamLeader;
        this.eventID = eventID;
        this.teamID = teamID;
        this.context = context;
        preferenceHelper = new PreferenceHelper(activityContext);
    }

    @NonNull
    @Override
    public ManageTeamAdapter.ManageTeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_manage_team, parent, false);
        return new ManageTeamAdapter.ManageTeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManageTeamAdapter.ManageTeamViewHolder holder, final int i) {

        holder.nameTextView.setText(members.get(i).getName());
        holder.cogniIdTextView.setText(members.get(i).getCogniId());
        if (!isTeamLeader) {
            holder.delete.setVisibility(View.GONE);
        } else {
            holder.delete.setVisibility(View.VISIBLE);
        }
        holder.delete.setOnClickListener(v -> {
            if (!isSelfDelete(members.get(i).getCogniId())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Member")
                        .setMessage("Are you sure you want to delete this member?")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            deleteMember(members.get(i).getCogniId(), teamID, eventID);
                        })
                        .setNegativeButton(android.R.string.no, (dialog, which) -> {
                            // do nothing
                        })
                        .show();
            } else {
                Toast.makeText(context, "You cannot remove yourself from team. Please unregister to do so.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    private void deleteMember(String cogniId, Integer teamID, Integer eventID) {

        apiClient.removeMember(preferenceHelper.getToken(), Integer.toString(eventID), Integer.toString(teamID), cogniId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        apiClient.fetchTeam(preferenceHelper.getToken(), Integer.toString(eventID))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<TeamResponse>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(TeamResponse teamResponse) {
                                        members = teamResponse.getTeam().getMembers();
                                        notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private boolean isSelfDelete(String cogniId) {

        if (cogniId.equals(preferenceHelper.getCogniId()))
            return true;
        else
            return false;

    }

    class ManageTeamViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView, cogniIdTextView;
        public ImageView delete;

        public ManageTeamViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.name);
            cogniIdTextView = view.findViewById(R.id.cogni_id_textView);
            delete = view.findViewById(R.id.cross_button);
        }
    }

}
