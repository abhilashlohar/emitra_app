package com.phppoets.grievance.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class VerticalLayoutManager extends LinearLayoutManager {
    private boolean canScrollVertically = true;

    public VerticalLayoutManager(Context context) {
        super(context);
    }

    @Override
    public boolean canScrollVertically() {
        return canScrollVertically;
    }

    public void setCanScrollVertically(boolean canScrollVertically) {
        this.canScrollVertically = canScrollVertically;
    }
}
