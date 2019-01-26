package com.mdgiitr.karthik.cognizance19.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.mdgiitr.karthik.cognizance19.R;

public class DashboardSPPFragment extends Fragment {
    private ProgressBar referalProgressBar;
    private ImageButton splitExcelButton;
    private LinearLayout excelCard;
    private boolean isExcelVisible = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard_spp, container, false);

        referalProgressBar = view.findViewById(R.id.referal_progress_bar);
        referalProgressBar.setProgress(48);

        splitExcelButton = view.findViewById(R.id.split_excel_card_button);
        excelCard = view.findViewById(R.id.excel_card);

        splitExcelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExcelVisible) {
                    excelCard.setVisibility(View.GONE);
                    isExcelVisible = false;
                    splitExcelButton.setImageResource(R.drawable.ic_add_black_24dp);
                } else {
                    excelCard.setVisibility(View.VISIBLE);
                    isExcelVisible = true;
                    splitExcelButton.setImageResource(R.drawable.ic_remove_black_24dp);
                }
            }
        });
        return view;
    }

}
