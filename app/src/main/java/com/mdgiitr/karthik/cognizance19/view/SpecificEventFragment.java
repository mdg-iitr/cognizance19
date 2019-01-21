package com.mdgiitr.karthik.cognizance19.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.mdgiitr.karthik.cognizance19.R;

import java.util.Timer;
import java.util.TimerTask;

public class SpecificEventFragment extends Fragment {
    private ImageSwitcher switcher;
    private Timer timer = null;
    private int[] gallery = {R.drawable.dark_blue_bg, R.drawable.ic_launcher_background, R.drawable.blue_button_bg, R.drawable.gray_round_card};
    private int position = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_specific_event, container, false);

        switcher = view.findViewById(R.id.specific_event_image_switcher);
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                ViewGroup.LayoutParams params = new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(params);
                return imageView;
            }
        });

        Animation fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        fadeIn.setDuration(2000);
        Animation fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_left);
        fadeOut.setDuration(2000);
        switcher.setInAnimation(fadeIn);
        switcher.setOutAnimation(fadeOut);

        start();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stop();
    }

    public void start()
    {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                // avoid exception: "Only the original thread that created a view hierarchy can touch its views"
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        switcher.setImageResource(gallery[position]);
                        position++;
                        if (position == 4)
                        {
                            position = 0;
                        }
                    }
                });
            }

        }, 0, 6000);

    }

    public void stop()
    {
        timer.cancel();
    }

}
