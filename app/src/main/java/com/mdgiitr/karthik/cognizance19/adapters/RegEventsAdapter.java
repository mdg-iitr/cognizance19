package com.mdgiitr.karthik.cognizance19.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.GeneralResponse;
import com.mdgiitr.karthik.cognizance19.models.RegEventsModel;
import com.mdgiitr.karthik.cognizance19.models.RegEventsResponse;
import com.mdgiitr.karthik.cognizance19.models.TeamMember;
import com.mdgiitr.karthik.cognizance19.models.TeamResponse;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class RegEventsAdapter extends RecyclerView.Adapter<RegEventsAdapter.RegEventsViewHolder> {

    Context context;
    private List<RegEventsModel> list;
    private ApiClient apiClient;
    private Dialog manageTeamDialog;
    private RecyclerView manageTeamRecyclerView;
    private ManageTeamAdapter manageTeamAdapter;
    private Button cancelButton, unregisterButton;
    private TextView addMemberView, cantAddMember;
    private RegEventsModel currentModel;
    private EditText idEditText;
    private PreferenceHelper preferenceHelper;
    private Context activityContext;
    private int PDF_RESULT_CODE = 1212;

    public RegEventsAdapter(Context activityContext, Context context, List<RegEventsModel> list) {
        this.context = context;
        this.list = list;
        apiClient = new ApiClient();
        preferenceHelper = new PreferenceHelper(activityContext);
        this.activityContext = activityContext;
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
        if (model.get_abstract()) {
            popupMenu.getMenuInflater().inflate(R.menu.reg_event_menu_with_abstract, popupMenu.getMenu());
        } else {
            popupMenu.getMenuInflater().inflate(R.menu.reg_event_menu, popupMenu.getMenu());
        }
        holder.moreButton.setOnClickListener((View v) -> popupMenu.show());
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.unregister) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Unregister");
                alert.setMessage("Unregister for " + model.getName() + "?");
                alert.setPositiveButton("Unregister", (dialog, which) -> {
                    apiClient.unregister(preferenceHelper.getToken(), Integer.toString(model.getId()))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<GeneralResponse>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(GeneralResponse generalResponse) {
                                    Toast.makeText(context, generalResponse.message, Toast.LENGTH_LONG).show();
                                    if (generalResponse.message.equals("Successfully Unregistered.")) {
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

                manageTeamDialog = new Dialog(context);
                manageTeamDialog.setContentView(R.layout.dialog_manage_team);
                manageTeamDialog.setCancelable(false);
                manageTeamRecyclerView = manageTeamDialog.findViewById(R.id.memberRecyclerView);
                cancelButton = manageTeamDialog.findViewById(R.id.cancel_button);
                unregisterButton = manageTeamDialog.findViewById(R.id.unregister_button);
                addMemberView = manageTeamDialog.findViewById(R.id.addMember);
                cantAddMember = manageTeamDialog.findViewById(R.id.cantAddMember);
                idEditText = manageTeamDialog.findViewById(R.id.idEditText);
                manageTeam(model);

            } else if (item.getItemId() == R.id.submit_abstract) {

//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("application/pdf");
//                ((Activity)(activityContext)).startActivityForResult(intent, PDF_RESULT_CODE);
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void manageTeam(RegEventsModel model) {

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data. Please Wait...");
        progressDialog.show();

        currentModel = model;

        apiClient.fetchTeam(preferenceHelper.getToken(), Integer.toString(model.getId()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TeamResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TeamResponse teamResponse) {
                        progressDialog.dismiss();
                        List<TeamMember> members = teamResponse.getTeam().getMembers();
                        boolean isTeamLeader = isRegisteredTeamLeader(members);
                        manageTeamAdapter = new ManageTeamAdapter(model.getTeamLimit(), members, isTeamLeader, teamResponse.getTeam().getId(), model.getId(), activityContext, context);
                        manageTeamRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        manageTeamRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                        manageTeamRecyclerView.setAdapter(manageTeamAdapter);
                        if (members.size() < model.getTeamLimit() && isTeamLeader) {
                            addMemberView.setVisibility(View.VISIBLE);
                            idEditText.setVisibility(View.VISIBLE);
                            cantAddMember.setVisibility(View.GONE);
                        } else {
                            addMemberView.setVisibility(View.GONE);
                            idEditText.setVisibility(View.GONE);
                            cantAddMember.setVisibility(View.VISIBLE);
                        }

                        addMemberView.setOnClickListener(v -> addMember(idEditText.getText(), teamResponse.getTeam().getId(), model.getId()));

                        cancelButton.setOnClickListener(v -> manageTeamDialog.dismiss());

                        unregisterButton.setOnClickListener(v -> unregisterMemberCheck(preferenceHelper.getToken(), model.getId(), isTeamLeader));

                        manageTeamDialog.show();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void unregisterMemberCheck(String token, Integer eventID, boolean isTeamLeader) {

        AlertDialog.Builder builder;
        builder = new android.app.AlertDialog.Builder(context);
        if (isTeamLeader) {
            builder.setTitle("Unregister")
                    .setMessage("Being the leader of your team, if you unregister your whole team will be unregistered. Do you still want to unregister?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        unregisterMember(token, eventID);
                    })
                    .setNegativeButton(android.R.string.no, (dialog, which) -> {
                        // do nothing
                    })
                    .show();
        } else {
            builder.setTitle("Unregister")
                    .setMessage("Are you sure you want to unregister?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        unregisterMember(token, eventID);
                    })
                    .setNegativeButton(android.R.string.no, (dialog, which) -> {
                        // do nothing
                    })
                    .show();
        }

    }

    private void unregisterMember(String token, Integer eventID) {

        apiClient.unregister(token, Integer.toString(eventID))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        Toast.makeText(context, generalResponse.message, Toast.LENGTH_SHORT).show();
                        manageTeamDialog.dismiss();
                        apiClient.fetchRegisteredEvents(preferenceHelper.getToken())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<RegEventsResponse>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(RegEventsResponse regEventsResponse) {
                                        int size = regEventsResponse.getEvents().size();
                                        list = new ArrayList<>();
                                        for (int i = 0; i < size; i++) {
                                            if (!regEventsResponse.getEvents().get(i).getType().equals("workshop")) {
                                                list.add(regEventsResponse.getEvents().get(i));
                                            }
                                        }
                                        notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Toast.makeText(context, "Error in fetching Registered Events.", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("UNREGISTER_ERROR", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private boolean isRegisteredTeamLeader(List<TeamMember> members) {

        String cogniId = preferenceHelper.getCogniId();

        for (TeamMember teamMember : members) {

            if (teamMember.getCogniId().equals(cogniId))
                return true;

        }
        return false;

    }

    private void addMember(CharSequence cogniId, Integer teamId, Integer eventId) {

        if (cogniId != null && !cogniId.toString().trim().isEmpty()) {

            String id = cogniId.toString().trim();

            apiClient.addMember(preferenceHelper.getToken(), Integer.toString(eventId), Integer.toString(teamId), id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {
                            Toast.makeText(context, "Added member", Toast.LENGTH_SHORT).show();
                            apiClient.fetchTeam(preferenceHelper.getToken(), Integer.toString(eventId))
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<TeamResponse>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(TeamResponse teamResponse) {
                                            idEditText.setText("");
                                            List<TeamMember> members = teamResponse.getTeam().getMembers();
                                            boolean isTeamLeader = isRegisteredTeamLeader(members);
                                            manageTeamAdapter = new ManageTeamAdapter(currentModel.getTeamLimit(), members, isTeamLeader, teamResponse.getTeam().getId(), currentModel.getId(), activityContext, context);
                                            manageTeamRecyclerView.setItemAnimator(new DefaultItemAnimator());
                                            manageTeamRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                                            manageTeamRecyclerView.setAdapter(manageTeamAdapter);
                                            if (members.size() < currentModel.getTeamLimit() && isTeamLeader) {
                                                addMemberView.setVisibility(View.VISIBLE);
                                                idEditText.setVisibility(View.VISIBLE);
                                                cantAddMember.setVisibility(View.GONE);
                                            } else {
                                                addMemberView.setVisibility(View.GONE);
                                                idEditText.setVisibility(View.GONE);
                                                cantAddMember.setVisibility(View.VISIBLE);
                                            }

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
                            Toast.makeText(context, "Unable to add member", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        } else {
            Toast.makeText(context, "Enter valid Cogni ID", Toast.LENGTH_SHORT).show();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("MyAdapter", "onActivityResult");
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
