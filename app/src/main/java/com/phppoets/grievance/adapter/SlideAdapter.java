package com.phppoets.grievance.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.phppoets.grievance.R;

import java.util.List;

/**
 * Created by vaibhav on 10/21/2016.
 */

public class SlideAdapter extends PagerAdapter
{

    Context mContext;
    LayoutInflater mLayoutInflater;
    String TAG = "AdvAdapter";

    private List<Drawable> sliderList;

    public SlideAdapter(Context context, List<Drawable> sliderList)
    {
        Log.e("", "**INSIDE**" + TAG);
        this.mContext = context;
        this.sliderList = sliderList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        // TODO Auto-generated method stub
        return sliderList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position)
    {

        final ViewHolder viewHolder;

        View itemView = mLayoutInflater.inflate(R.layout.custom_slide, container, false);
        viewHolder = new ViewHolder();
        viewHolder.pgrImage = (ImageView) itemView.findViewById(R.id.custom_slide_img);

        Drawable imageUrls = sliderList.get(position);

        viewHolder.pgrImage.setImageDrawable(imageUrls);

        itemView.setTag(viewHolder);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((LinearLayout) object);
    }

    class ViewHolder
    {
        ImageView pgrImage;
    }
}
