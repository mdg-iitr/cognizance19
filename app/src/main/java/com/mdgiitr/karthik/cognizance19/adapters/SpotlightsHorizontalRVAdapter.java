package com.mdgiitr.karthik.cognizance19.adapters;

import android.content.Context;
import android.media.Image;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdgiitr.karthik.cognizance19.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SpotlightsHorizontalRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Image> mImageList;
    private List<String> mText_view_1_list;
    private List<String> mText_view_2_list;
    private int mRowIndex = -1;

    public void setData(List<Image> data1, List<String> data2, List<String> data3){
        if(mImageList != data1){
            mImageList = data1;
            notifyDataSetChanged();
        }

        if(mText_view_1_list != data2){
            mText_view_1_list = data2;
            notifyDataSetChanged();
        }

        if(mText_view_2_list != data3){
            mText_view_2_list = data3;
            notifyDataSetChanged();
        }
    }

    public void setRowIndex(int index){
        mRowIndex = index;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_HRV;
        private TextView textView_HRV_1;
        private TextView textView_HRV_2;

        public ItemViewHolder(View itemView){
            super(itemView);
            imageView_HRV = (ImageView) itemView.findViewById(R.id.horizontal_card_image);
            textView_HRV_1 = (TextView) itemView.findViewById(R.id.horizontal_card_text_1);
            textView_HRV_2 = (TextView) itemView.findViewById(R.id.horizontal_card_text_2);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.spotlights_horizontal_rv_items, viewGroup, false);
        ItemViewHolder holder = new ItemViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ItemViewHolder holder = (ItemViewHolder) viewHolder;
        holder.imageView_HRV.setImageResource(R.drawable.fake_profile);
        holder.textView_HRV_1.setText(mText_view_1_list.get(i));
        holder.textView_HRV_2.setText(mText_view_2_list.get(i));
        holder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return mText_view_1_list.size();
    }
}
