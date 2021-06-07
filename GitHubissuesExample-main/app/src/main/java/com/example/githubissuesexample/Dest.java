package com.example.githubissuesexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dest extends AppCompatActivity {

    private TextView textView;
    CustomCommentAdapter commentAdapter;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_tile);

        textView = (TextView) findViewById(R.id.comment);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        textView.setText(str);

        GetDataService service = RetrofitClient.getRetrofit().create(GetDataService.class);

        Call<List<Comments>> call1 = service.getAllComments(str);
        call1.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call1, Response<List<Comments>> response) {
              //  Toast.makeText(Dest.this, "test", Toast.LENGTH_LONG).show();
                getComment(response.body());


            }

            @Override
            public void onFailure(Call<List<Comments>> call1, Throwable t) {

               // Toast.makeText(Dest.this, t.getMessage(), Toast.LENGTH_LONG).show();


            }
        });


    }

    private void getComment(List<Comments>body) {

        recyclerView=findViewById(R.id.recyclecom);
        commentAdapter= new CustomCommentAdapter(Dest.this,body);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Dest.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(commentAdapter);
    }

}
