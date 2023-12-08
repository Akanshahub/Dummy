package com.example.sqldemo3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class CustomerAdapter extends ArrayAdapter<CustomerModel> {


    // View lookup cache
    private static class ViewHolder {
        TextView id;
        TextView name;
    }

    public CustomerAdapter(Context context, List<CustomerModel> products) {
        super(context, R.layout.single_item_view, products);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CustomerModel customerModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.single_item_view, parent, false);
            viewHolder.id = (TextView) convertView.findViewById(R.id.single);

            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        assert customerModel != null;

        viewHolder.id.setText("Id:"+customerModel.getId() + ", Name: " +customerModel.getName() + ", Age: " +customerModel.getAge() + ", isActive: " +customerModel.isActive());

        // Return the completed view to render on screen
        return convertView;
    }
}