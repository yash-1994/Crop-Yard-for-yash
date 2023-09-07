package com.example.cropyard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CropIListtemAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] items;
    private final int[] imageIds;

    public CropIListtemAdapter(Context context, String[] items, int[] imageIds) {
        super(context, R.layout.custom_add_crop_spinner_item, items);
        this.context = context;
        this.items = items;
        this.imageIds = imageIds;
    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }



    private View createItemView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_add_crop_spinner_item, parent, false);

        ImageView imageView = rowView.findViewById(R.id.imageView);
        TextView textView = rowView.findViewById(R.id.textView);

        imageView.setImageResource(imageIds[position]);
        textView.setText(items[position]);

        return rowView;
    }
}
