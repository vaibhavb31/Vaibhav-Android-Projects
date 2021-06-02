package com.example.Retrofitviewbinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.Retrofitviewbinding.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter customAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityMainBinding activityMainBinding;

        super.onCreate(savedInstanceState);

        activityMainBinding= ActivityMainBinding.inflate(getLayoutInflater());
        View view= activityMainBinding.getRoot();
        setContentView(view);
        recyclerView= activityMainBinding.recyclerview;





        GetDataService service= RetrofitClient.getRetrofit().create(GetDataService.class);

        Call<UserResponce> call= service.getAllUsers();
        call.enqueue(new Callback<UserResponce>() {
            @Override
            public void onResponse(Call<UserResponce> call, Response<UserResponce> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<UserResponce> call, Throwable t) {

            }
        });

    }
    private void generateDataList(UserResponce body) {

        customAdapter = new CustomAdapter(body.getData());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(customAdapter);





    }
}
