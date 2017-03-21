package com.phppoets.grievance.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.phppoets.grievance.R;
import com.phppoets.grievance.model.notification.Result;
import com.phppoets.grievance.support.UIUtils;
import com.phppoets.grievance.utility.TSTypeface;

import java.util.List;

/**
 * Created by user on 3/18/2017.
 */
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    SharedPreferences preferences;
    Context mcontext;
    ClickListener clickListener;
    List<Result> notificationList;

    String option;
    private LayoutInflater inflater;

    public NotificationAdapter(Context mcontext, List<Result> notificationList) {

        this.mcontext = mcontext;
        this.notificationList = notificationList;
        inflater = LayoutInflater.from(mcontext);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_notification, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.txtTitle.setText(notificationList.get(position).getButtonText());
        holder.txtDiscription.setText(notificationList.get(position).getMessage());
        holder.txtTimestamp.setText(notificationList.get(position).getTime());
        holder.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.ItemClicked(view, position, "");
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return notificationList.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void ItemClicked(View v, int position, String Tag);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle, txtDiscription, txtTimestamp;
        public ImageView imgNotification;
        public RelativeLayout rlMain;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtDiscription = (TextView) itemView.findViewById(R.id.txtDiscription);
            txtTimestamp = (TextView) itemView.findViewById(R.id.txtTimestamp);
            imgNotification = (ImageView) itemView.findViewById(R.id.imgNotification);
            rlMain = (RelativeLayout) itemView.findViewById(R.id.rlMain);
            setFonts();
        }

        public void setFonts() {
            txtTitle.setTypeface(UIUtils.getTypeface(mcontext, TSTypeface.MEDIUM));
            txtDiscription.setTypeface(UIUtils.getTypeface(mcontext, TSTypeface.MEDIUM));
            txtTimestamp.setTypeface(UIUtils.getTypeface(mcontext, TSTypeface.LIGHT));
        }
    }

}


