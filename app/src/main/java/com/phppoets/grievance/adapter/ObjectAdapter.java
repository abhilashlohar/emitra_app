package com.phppoets.grievance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.phppoets.grievance.Interface.DataTransferInterface;
import com.phppoets.grievance.R;
import com.phppoets.grievance.support.UIUtils;
import com.phppoets.grievance.utility.TSTypeface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2/7/2017.
 */
public class ObjectAdapter extends BaseAdapter implements Filterable {
    DataTransferInterface dtInterface;
    private Context context;
    private List<String> originalList;
    private List<String> suggestions = new ArrayList<>();
    private Filter filter = new CustomFilter();

    /**
     * @param context      Context
     * @param originalList Original list used to compare in constraints.
     */
    public ObjectAdapter(Context context, List<String> originalList, DataTransferInterface dtInterface) {
        this.context = context;
        this.originalList = originalList;
        this.dtInterface = dtInterface;
    }

    @Override
    public int getCount() {
        return suggestions.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        final ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.simple_text_view,
                    parent,
                    false);
            holder = new ViewHolder();
            holder.autoText = (TextView) convertView.findViewById(R.id.textView);
            holder.autoText.setTypeface(UIUtils.getTypeface(context, TSTypeface.MEDIUM));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.autoText.setText(suggestions.get(position));
        holder.autoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dtInterface.setValues(suggestions.get(position), position);
            }
        });

        //dtInterface.setValues(suggestions.get(position));
        return convertView;

    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private static class ViewHolder {
        TextView autoText;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            suggestions.clear();

            if (originalList != null && constraint != null) { // Check if the Original List and Constraint aren't null.
                for (int i = 0; i < originalList.size(); i++) {
                    if (originalList.get(i).contains(constraint.toString().toLowerCase())) { // Compare item in original list if it contains constraints.
                        suggestions.add(originalList.get(i).toString()); // If TRUE add item in Suggestions.
                    }
                }
            }

            FilterResults results = new FilterResults(); // Create new Filter Results and return this to publishResults;
            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

}



