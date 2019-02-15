package com.mdgiitr.karthik.cognizance19.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mdgiitr.karthik.cognizance19.R;

import java.util.ArrayList;
import java.util.Collections;

public class EventRegisterIDsAdapter extends RecyclerView.Adapter<EventRegisterIDsAdapter.EventRegisterViewHolder> {

    private ArrayList<String> ids;
    private int teamLimit = 0;

    public EventRegisterIDsAdapter(int teamLimit) {
        this.teamLimit = teamLimit;
        ids = new ArrayList<String>(Collections.nCopies(teamLimit - 1, ""));
    }

    @NonNull
    @Override
    public EventRegisterIDsAdapter.EventRegisterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_event_register_edit_text, parent, false);
        return new EventRegisterIDsAdapter.EventRegisterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventRegisterIDsAdapter.EventRegisterViewHolder holder, final int i) {

        holder.idEditText.setHint("Member " + (i + 2) + " ID");
        holder.idEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ids.set(i, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return teamLimit - 1;
    }

    public ArrayList<String> getIds() {
        return ids;
    }

    class EventRegisterViewHolder extends RecyclerView.ViewHolder {
        public EditText idEditText;

        public EventRegisterViewHolder(View view) {
            super(view);
            idEditText = view.findViewById(R.id.member_id_editText);
        }
    }

}
