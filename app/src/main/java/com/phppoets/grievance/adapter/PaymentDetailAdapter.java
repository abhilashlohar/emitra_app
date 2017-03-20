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
 * Created by user on 2/10/2017.
 */
public class PaymentDetailAdapter extends RecyclerView.Adapter<PaymentDetailAdapter.MyViewHolder> {
    ClickListener clickListener;
    private List<PaymentService> paymentDetailList;
    private Activity mContext;

    public PaymentDetailAdapter(Activity context, List<PaymentService> paymentDetailList) {
        this.mContext = context;
        this.paymentDetailList = paymentDetailList;
    }

    @Override
    public PaymentDetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_payment, parent, false);
        return new PaymentDetailAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PaymentDetailAdapter.MyViewHolder holder, final int position) {
        holder.textViewPayment.setText(paymentDetailList.get(position).getServiceName());
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
        public TextView textViewPayment, txtNewsSubTitle, txtNewsDate, textViewShare;
        public ImageView imgNews, imgShare;
        public LinearLayout linearLayoutNews, llShare;
        RelativeLayout Rl1;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewPayment = (TextView) itemView.findViewById(R.id.textViewPayment);
            Rl1 = (RelativeLayout) itemView.findViewById(R.id.RL1);
            setFonts();
        }

        public void setFonts() {
            textViewPayment.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.MEDIUM));
            /*txtNewsSubTitle.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.LIGHT));
            txtNewsDate.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.LIGHT));
            textViewShare.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.LIGHT));*/
        }
    }
}


