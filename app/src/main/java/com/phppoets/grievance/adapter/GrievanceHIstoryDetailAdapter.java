package com.phppoets.grievance.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phppoets.grievance.R;
import com.phppoets.grievance.model.grievanceHistoryDetail.Result;
import com.phppoets.grievance.support.UIUtils;
import com.phppoets.grievance.utility.TSTypeface;

import java.util.List;

/**
 * Created by user on 2/15/2017.
 */
public class GrievanceHIstoryDetailAdapter extends RecyclerView.Adapter<GrievanceHIstoryDetailAdapter.MyViewHolder> {

    String qtyType;
    private List<Result> myCalenderList;
    private Activity mContext;
    //FetchCalenderCallListener listener;

    public GrievanceHIstoryDetailAdapter(Activity context, List<Result> myCalenderList) {
        this.mContext = context;
        this.myCalenderList = myCalenderList;

    }


    @Override
    public GrievanceHIstoryDetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_grievance_detail_history, parent, false);


        return new GrievanceHIstoryDetailAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GrievanceHIstoryDetailAdapter.MyViewHolder holder, int position) {


        holder.txtDate.setText(myCalenderList.get(position).getTime());
        //  holder.txtMonth.setText(myCalenderList.get(position).getTime());
        //  holder.txtYear.setText(myCalenderList.get(position).getTime());
        holder.txtDescription.setText(myCalenderList.get(position).getDescription());


    }

    @Override
    public int getItemCount() {

        return myCalenderList.size();


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtMonth, txtYear, txtDescription;


        public MyViewHolder(View itemView) {
            super(itemView);
            txtDate = (TextView) itemView.findViewById(R.id.txtEventDate);
            txtMonth = (TextView) itemView.findViewById(R.id.txtEventMonth);
            txtYear = (TextView) itemView.findViewById(R.id.txtEventYear);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            setFonts();
        }

        private void setFonts() {

            txtDate.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.MEDIUM));
            txtMonth.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.MEDIUM));
            txtYear.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.MEDIUM));

        }
    }
}
