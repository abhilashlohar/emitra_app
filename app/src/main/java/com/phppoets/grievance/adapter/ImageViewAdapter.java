package com.phppoets.grievance.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.phppoets.grievance.R;
import com.phppoets.grievance.support.UIUtils;
import com.phppoets.grievance.utility.TSTypeface;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import in.myinnos.awesomeimagepicker.models.Image;

/**
 * Created by user on 3/20/2017.
 */


/**
 * Created by user on 2/10/2017.
 */
public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewAdapter.MyViewHolder> {
    ClickListener clickListener;
    private List<Image> imageList;
    private Activity mContext;

    public ImageViewAdapter(Activity context, List<Image> imageList) {
        this.mContext = context;
        this.imageList = imageList;
    }

    @Override
    public ImageViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_imageview, parent, false);
        return new ImageViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ImageViewAdapter.MyViewHolder holder, final int position) {
        Uri uri = Uri.fromFile(new File(imageList.get(position).path));
        Picasso.with(mContext).load(uri).into(holder.imgView);

        // holder.txtLabelName.setText(billDetailList.get(position).getLableName());
        // holder.txtLabelValue.setText(billDetailList.get(position).getLableValue());

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void ItemClicked(View v, int position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtLabelName, txtLabelValue, txtNewsDate, textViewShare;
        public ImageView imgView, imgShare;
        public LinearLayout linearLayoutNews, llShare;
        RelativeLayout Rl1;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.imgView);
        }

        public void setFonts() {
            txtLabelValue.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.MEDIUM));
            txtLabelName.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.MEDIUM));
            /*txtNewsSubTitle.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.LIGHT));
            txtNewsDate.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.LIGHT));
            textViewShare.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.LIGHT));*/
        }
    }
}


