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
import com.phppoets.grievance.model.paymentHistory.Result;
import com.phppoets.grievance.support.UIUtils;
import com.phppoets.grievance.utility.TSTypeface;

import java.util.List;

/**
 * Created by user on 3/21/2017.
 */


public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.MyViewHolder> {
    ClickListener clickListener;
    private List<Result> paymentDetailList;
    private Activity mContext;

    public PaymentHistoryAdapter(Activity context, List<Result> paymentDetailList) {
        this.mContext = context;
        this.paymentDetailList = paymentDetailList;
    }

    @Override
    public PaymentHistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_payment_history, parent, false);
        return new PaymentHistoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PaymentHistoryAdapter.MyViewHolder holder, final int position) {
        holder.txtTimestamp.setText(paymentDetailList.get(position).getPaymentTime());
        holder.txtAmountShow.setText(String.valueOf(paymentDetailList.get(position).getAmount()));
        holder.txtPNRNo.setText(paymentDetailList.get(position).getPrn());
        holder.txtResponse.setText(paymentDetailList.get(position).getResponce());


       /* holder.Rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, PaymentDetailActivity.class).putExtra("data", paymentDetailList.get(position)
                        .getSampleDataDec())
                        .putExtra("id", "1"));
            }
        });*/
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
        public TextView txtResponse, txtPNRNo, txtAmountShow, txtTimestamp;
        public ImageView imgNews, imgShare;
        public LinearLayout linearLayoutNews, llShare;
        RelativeLayout Rl1;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtResponse = (TextView) itemView.findViewById(R.id.txtResponse);
            txtPNRNo = (TextView) itemView.findViewById(R.id.txtPNRNo);
            txtAmountShow = (TextView) itemView.findViewById(R.id.txtAmountShow);
            txtTimestamp = (TextView) itemView.findViewById(R.id.txtTimestamp);


            //Rl1 = (RelativeLayout) itemView.findViewById(R.id.RL1);
            setFonts();
        }

        public void setFonts() {
            txtResponse.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.MEDIUM));
            txtPNRNo.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.LIGHT));
            txtAmountShow.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.LIGHT));
            txtTimestamp.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.LIGHT));
        }
    }
}




