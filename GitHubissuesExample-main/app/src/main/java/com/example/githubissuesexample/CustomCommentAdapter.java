package com.example.githubissuesexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

 class CustomCommentAdapter extends RecyclerView.Adapter<CustomCommentAdapter.CutomCommentViewHolder> {
    Context context;
    List<Comments> dataComments;
    List<Users> dataUsers;
    String url;

    public class CutomCommentViewHolder extends RecyclerView.ViewHolder{

        private View mview;

        private TextView body;
        private TextView strcom;
        private TextView login;
        private ImageView imageView;

        public CutomCommentViewHolder(View itemView) {
            super(itemView);
            mview=itemView;
            body= mview.findViewById(R.id.body);
            strcom= mview.findViewById(R.id.comment);
            login=mview.findViewById(R.id.user);
            imageView= mview.findViewById(R.id.userImg);

        }
    }

    public CustomCommentAdapter(Context context, List<Comments> dataComments) {
        this.context = context;
        this.dataComments = dataComments;
    }


    @Override
    public CutomCommentViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.comment_tile, parent, false);
        return new CutomCommentViewHolder(view);
    }

     @Override
     public void onBindViewHolder(CustomCommentAdapter.CutomCommentViewHolder holder, int position) {

        String cal=dataComments.get(position).getBody();
        if(cal.isEmpty()) {
           // holder.body.setText("No Comments Found");
        }
        else{
            holder.body.setText(dataComments.get(position).getBody());

        }
        holder.login.setText(dataComments.get(position).getUser().getLogin());
         Glide.with(context).load(dataComments.get(position).getUser().getAvatar_url())
                 .into(holder.imageView);

         url= holder.strcom.getText().toString();

     }



    @Override
    public int getItemCount() {
        return dataComments.size();
    }
}
