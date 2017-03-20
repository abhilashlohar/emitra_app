package com.phppoets.grievance.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.phppoets.grievance.R;
import com.phppoets.grievance.activity.PaymentDetailActivity;
import com.phppoets.grievance.model.payment.PaymentService;
import com.phppoets.grievance.support.UIUtils;
import com.phppoets.grievance.utility.TSTypeface;

import java.util.List;

/**
 * Created by user on 3/21/2017.
 */


public class GrievanceHistoryAdapter extends RecyclerView.Adapter<GrievanceHistoryAdapter.MyViewHolder> {
    ClickListener clickListener;
    private List<PaymentService> paymentDetailList;
    private Activity mContext;

    public GrievanceHistoryAdapter(Activity context, List<PaymentService> paymentDetailList) {
        this.mContext = context;
        this.paymentDetailList = paymentDetailList;
    }

    @Override
    public GrievanceHistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_grievance_histrory, parent, false);
        return new GrievanceHistoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GrievanceHistoryAdapter.MyViewHolder holder, final int position) {
        holder.txtSubject.setText(paymentDetailList.get(position).getServiceName());
        holder.txtDepartment.setText(paymentDetailList.get(position).getServiceName());
        holder.txtDescription.setText(paymentDetailList.get(position).getServiceName());
        holder.txtTimestamp.setText(paymentDetailList.get(position).getServiceName());
        holder.Rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, PaymentDetailActivity.class).putExtra("data", paymentDetailList.get(position)
                        .getSampleDataDec())
                        .putExtra("id", "1"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return paymentDetailList.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void ItemClicked(View v, int position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtSubject, txtDepartment, txtDescription, txtTimestamp;
        public ImageView imgNews, imgShare;
        public LinearLayout linearLayoutNews, llShare;
        RelativeLayout Rl1;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtSubject = (TextView) itemView.findViewById(R.id.txtSubject);
            txtDepartment = (TextView) itemView.findViewById(R.id.txtDepartment);

            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);

            txtTimestamp = (TextView) itemView.findViewById(R.id.txtTimestamp);


            setFonts();
        }

        public void setFonts() {
            txtSubject.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.MEDIUM));
            txtDepartment.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.LIGHT));
            txtDescription.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.LIGHT));
            txtTimestamp.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.LIGHT));
        }
    }
}






