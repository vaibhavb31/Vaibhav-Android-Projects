package com.example.dbcontact.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbcontact.DeleteContact;
import com.example.dbcontact.Model.Contacts;
import com.example.dbcontact.databinding.RowTileBinding;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private List<Contacts> datalist;

    public CustomAdapter(List<Contacts> datalist) {
        this.datalist = datalist;
    }

    @Override
    public CustomViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new CustomViewHolder(RowTileBinding.inflate(LayoutInflater.from(parent.getContext())
        ,parent,false));
    }

    @Override
    public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {

        holder.rowTileBinding.nametxt.setText(datalist.get(position).getName());
        holder.rowTileBinding.phonetxt.setText(datalist.get(position).getPhoneNumber());

        String Name= datalist.get(position).getName();
        String Phone= datalist.get(position).getPhoneNumber();
        int Id= datalist.get(position).getId();

        holder.rowTileBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(holder.rowTileBinding.getRoot().getContext(), DeleteContact.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name",Name);
                intent.putExtra("phone", Phone);
                intent.putExtra("id",Id);
                holder.rowTileBinding.getRoot().getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public void refreshList(List<Contacts> allContacts) {
        this.datalist= allContacts;
        notifyDataSetChanged();
    }

    public void updateList(List<Contacts> list){
        this.datalist = list;
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        private RowTileBinding rowTileBinding;


        public CustomViewHolder(RowTileBinding rowTileBinding) {
            super(rowTileBinding.getRoot());
            this.rowTileBinding= rowTileBinding;
        }
    }


}
