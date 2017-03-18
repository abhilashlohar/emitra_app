package com.phppoets.grievance.support;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

public class SmoothViewPager extends ViewPager {

    private ScrollerCustomDuration mScroller = null;

    public SmoothViewPager(Context context) {
        super(context);
        postInitViewPager();
    }

    public SmoothViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        postInitViewPager();
    }

    /**
     * Override the Scroller instance with our own class so we can change the
     * duration
     */
    private void postInitViewPager() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = viewpager.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            mScroller = new ScrollerCustomDuration(getContext(), (Interpolator) interpolator.get(null));

            scroller.set(this, mScroller);
        } catch (Exception e) {
            //Utility.printLog("MyPager", e.getMessage());
        }

/*		 try {
                Field scroller = ViewPager.class.getDeclaredField("mScroller");
	            scroller.setAccessible(true);
	            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
	            interpolator.setAccessible(true);

	            mScroller = new ScrollerCustomDuration(getContext(),
	                    (Interpolator) interpolator.get(null));
	            scroller.set(this, mScroller);
	        } catch (Exception e) {
	        }*/
    }

    /**
     * Set the factor by which the duration will change
     */
    public void setScrollDurationFactor(double scrollFactor) {
        mScroller.setScrollDurationFactor(scrollFactor);
    }
}