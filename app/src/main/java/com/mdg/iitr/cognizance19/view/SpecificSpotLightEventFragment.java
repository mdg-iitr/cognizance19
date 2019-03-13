package com.mdg.iitr.cognizance19.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.adapters.EventRegisterIDsAdapter;
import com.mdg.iitr.cognizance19.models.Contact;
import com.mdg.iitr.cognizance19.models.EventResponse;
import com.mdg.iitr.cognizance19.models.EventSpecificModel;
import com.mdg.iitr.cognizance19.models.GeneralResponse;
import com.mdg.iitr.cognizance19.network.client.ApiClient;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

import static com.mdg.iitr.cognizance19.MainActivity.bottomNavigationView;
import static com.mdg.iitr.cognizance19.MainActivity.navController;

public class SpecificSpotLightEventFragment extends Fragment {

    public static String title = "";
    public static String innerHtml ="";
    public static String html = "<html><body>"+innerHtml+"</body></html>";
    String mime = "text/html";
    String encoding = "utf-8";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_specific_spotlight_event, container, false);
        TextView eventName = view.findViewById(R.id.specific_event_name);
        WebView webView = view.findViewById(R.id.webView);
        eventName.setText(title);
        webView.getSettings().setLoadsImagesAutomatically(true);
        innerHtml = innerHtml.replace("https://", "http://");
        Spanned spanned = Html.fromHtml(innerHtml);
        webView.loadData("<!DOCTYPE html><html><body>"+spanned.toString()+"</body></html>", mime, encoding );
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
