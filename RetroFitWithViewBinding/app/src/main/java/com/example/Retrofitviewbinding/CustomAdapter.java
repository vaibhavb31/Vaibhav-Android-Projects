package com.example.Retrofitviewbinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.recyclerview.widget.RecyclerView;


import com.example.Retrofitviewbinding.databinding.RowTileBinding;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private List<RetroUsers> datalist;

    public CustomAdapter(List<RetroUsers> datalist) {
        this.datalist = datalist;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        private RowTileBinding rowTileBinding;


        public CustomViewHolder( RowTileBinding rowTileBinding) {
            super(rowTileBinding.getRoot());
            this.rowTileBinding = rowTileBinding;
        }
    }





    public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomViewHolder(RowTileBinding.inflate(LayoutInflater.from(parent.getContext())
                ,parent,false
                ));
    }
 @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.rowTileBinding.name.setText(datalist.get(position).getName());
        holder.rowTileBinding.status.setText(datalist.get(position).getStatus());
        holder.rowTileBinding.email.setText(datalist.get(position).getEmail());
        holder.rowTileBinding.gender.setText(datalist.get(position).getGender());


    }




    @Override
    public int getItemCount() {
        return datalist.size();
    }
}