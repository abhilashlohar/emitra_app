package com.phppoets.grievance.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.phppoets.grievance.R;
import com.phppoets.grievance.model.notification.fetchdetail.BillDetail;
import com.phppoets.grievance.support.UIUtils;
import com.phppoets.grievance.utility.TSTypeface;

import java.util.List;


/**
 * Created by user on 2/10/2017.
 */
public class BillDetailAdapter extends RecyclerView.Adapter<BillDetailAdapter.MyViewHolder> {
    ClickListener clickListener;
    private List<BillDetail> billDetailList;
    private Activity mContext;

    public BillDetailAdapter(Activity context, List<BillDetail> billDetailList) {
        this.mContext = context;
        this.billDetailList = billDetailList;
    }

    @Override
    public BillDetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_bill_detail, parent, false);
        return new BillDetailAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BillDetailAdapter.MyViewHolder holder, final int position) {
        holder.txtLabelName.setText(billDetailList.get(position).getLableName());
        holder.txtLabelValue.setText(billDetailList.get(position).getLableValue());

    }

    @Override
    public int getItemCount() {
        return billDetailList.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void ItemClicked(View v, int position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtLabelName, txtLabelValue, txtNewsDate, textViewShare;
        public ImageView imgNews, imgShare;
        public LinearLayout linearLayoutNews, llShare;
        RelativeLayout Rl1;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtLabelName = (TextView) itemView.findViewById(R.id.txtLabelName);
            txtLabelValue = (TextView) itemView.findViewById(R.id.txtLabelValue);
            Rl1 = (RelativeLayout) itemView.findViewById(R.id.RL1);
            setFonts();
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


