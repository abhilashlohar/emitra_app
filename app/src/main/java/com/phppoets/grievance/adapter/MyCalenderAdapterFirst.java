/*
package com.phppoets.grievance.adapter;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phppoets.grievance.R;
import com.phppoets.grievance.support.UIUtils;
import com.phppoets.grievance.utility.TSTypeface;

import java.util.List;

*/
/**
 * Created by user on 2/15/2017.
 *//*

public class MyCalenderAdapterFirst extends RecyclerView.Adapter<MyCalenderAdapterFirst.MyViewHolder> {

    private List<Responce> myCalenderList;
    private List<AddEvent> addEvent;

    MyCalenderAdapterSecond myCalenderAdapterSecond;
    String qtyType;
    private Activity mContext;
    FetchCalenderCallListener listener;

    public MyCalenderAdapterFirst(Activity context, List<Responce> myCalenderList, FetchCalenderCallListener listener) {
        this.mContext = context;
        this.myCalenderList = myCalenderList;
        this.listener = listener;
    }


    @Override
    public MyCalenderAdapterFirst.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_grievance_detail_history, parent, false);


        return new MyCalenderAdapterFirst.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyCalenderAdapterFirst.MyViewHolder holder, int position) {

        holder.txtDate.setText(myCalenderList.get(position).getDateTime().getDate());
        holder.txtMonth.setText(myCalenderList.get(position).getDateTime().getMonth());
        holder.txtYear.setText(myCalenderList.get(position).getDateTime().getYear());
        //  myCalenderList = new ArrayList<Responce>();
        holder.rv_myCalender.setHasFixedSize(true);
        holder.rv_myCalender.setItemAnimator(new DefaultItemAnimator());
        holder.rv_myCalender.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
           */
/* if (myCalenderAdapterSecond == null) {
                myCalenderAdapterSecond = new MyCalenderAdapterSecond(mContext, myCalenderList.get(position).getAddEvent(), "");
            } else {
                myCalenderAdapterSecond.notifyDataSetChanged();
            }*//*



    }

    @Override
    public int getItemCount() {

        return myCalenderList.size();


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtMonth, txtYear;
        RecyclerView rv_myCalender;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtDate = (TextView) itemView.findViewById(R.id.txtEventDate);
            txtMonth = (TextView) itemView.findViewById(R.id.txtEventMonth);
            txtYear = (TextView) itemView.findViewById(R.id.txtEventYear);
            rv_myCalender = (RecyclerView) itemView.findViewById(R.id.rv_calender_events_first);
            setFonts();
        }

        private void setFonts() {

            txtDate.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.MEDIUM));
            txtMonth.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.MEDIUM));
            txtYear.setTypeface(UIUtils.getTypeface(mContext, TSTypeface.MEDIUM));

        }
    }
}
*/
