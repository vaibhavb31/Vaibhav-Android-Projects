package com.example.flickrphotosearchapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flickrphotosearchapp.databinding.PhotoGridBinding;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
   private  List<Photos> datalist;

    public CustomAdapter( List<Photos> datalist) {
        this.datalist = datalist;
        //this.datalist.add((Photos) datalist);
    }

    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new CustomViewHolder(PhotoGridBinding.inflate
                (LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder( CustomAdapter.CustomViewHolder holder, int position) {
        String id =  datalist.get(position).getId();
        String farm= datalist.get(position).getFarm();
        String secret= datalist.get(position).getSecret();
        String server= datalist.get(position).getServer();
        String imgUrl="https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+"_m.jpg";

        Glide.with(holder.photoGridBinding.getRoot().getContext())
                .load(imgUrl).into(holder.photoGridBinding.imageView2);


           holder.photoGridBinding.textView2.setText(datalist.get(position).getTitle());
        //Toast.makeText(holder.photoGridBinding.getRoot().getContext(),"Vaaaaa"+datalist.getPhoto().size(),Toast.LENGTH_LONG).show();

    }

    public void refreshData( List<Photos> photosList){
       this.datalist = photosList;

        notifyDataSetChanged();
    }
    public void addItems(List<Photos> newItems) {
        int previousItemsSize = datalist.size();
        // Append the new items to the old items
        datalist.addAll(newItems);
        // Notify the adapter about the newly added items
        //notifyItemRangeInserted(previousItemsSize, newItems.size());
        notifyItemInserted(newItems.size());
    }

    @Override
    public int getItemCount() {

        return datalist.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
    private PhotoGridBinding photoGridBinding;

        public CustomViewHolder( PhotoGridBinding photoGridBinding) {
            super(photoGridBinding.getRoot());
            this.photoGridBinding=photoGridBinding;
        }
    }
}
