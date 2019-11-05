package com.example.plates;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    public ArrayList<String> data;

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;
        public ItemViewHolder (TextView v) {
            super(v);
            tv = v;
        }
    }

    public ItemAdapter(ArrayList<String> data) {
        this.data = data;
    }

    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder vh, int pos) {
        vh.tv.setText("");
        for (String s : data) {
            vh.tv.append(s + "\n");
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
