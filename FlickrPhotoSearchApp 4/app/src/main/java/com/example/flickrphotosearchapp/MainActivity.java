package com.example.flickrphotosearchapp;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flickrphotosearchapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.internal.http.HttpHeaders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter customAdapter;
    private RecyclerView recyclerView;
    private boolean iscrolling = false;
    private int page=1;
    String searchText="cat";
    int currentItem, totalItem, scrolledItem=0;


    ActivityMainBinding activityMainBinding;
    ArrayList data= new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding= ActivityMainBinding.inflate(getLayoutInflater());
        View view= activityMainBinding.getRoot();
        setContentView(view);
        //Manager= new LinearLayoutManager(this);
        recyclerView= activityMainBinding.recycle;
        customAdapter = new CustomAdapter(data);

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(customAdapter);

        getData(page,searchText);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged( RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


            }

            @Override
            public void onScrolled( RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem = layoutManager.getChildCount();
                totalItem = layoutManager.getItemCount();
                scrolledItem = layoutManager.findLastCompletelyVisibleItemPosition();

                if(dy>0)
                {
                    if(totalItem== scrolledItem+1)
                    {
                        page++;
                        performPagination(page,searchText);
                    }


                }
            }
        });

    }

    private void performPagination(int a,String s) {

        GetDataApi getDataApi=RetrofitClient.getRetrofit().create(GetDataApi.class);

        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("text",searchText);
        dataMap.put("page",""+a+"");
        dataMap.put("per_page","20");
        dataMap.put("method","flickr.photos.search");
        dataMap.put("api_key","062a6c0c49e4de1d78497d13a7dbb360");
        dataMap.put("format","json");
        dataMap.put("nojsoncallback","1");

        Call<ApiResponce> call= getDataApi.getdata(dataMap);
        call.enqueue(new Callback<ApiResponce>() {
            @Override
            public void onResponse(Call<ApiResponce> call, Response<ApiResponce> response) {
                int r= new Integer(response.body().getPhotos().getPages());
                if(page<=r)
                {
                    generateDataList(response.body());

                }

            }

            @Override
            public void onFailure(Call<ApiResponce> call, Throwable t) {
               // Toast.makeText(MainActivity.this,"failed"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getData(int a,String s) {
        GetDataApi getDataApi=RetrofitClient.getRetrofit().create(GetDataApi.class);

        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("text",searchText);
        dataMap.put("page",""+a+"");
        dataMap.put("per_page","20");
        dataMap.put("method","flickr.photos.search");
        dataMap.put("api_key","062a6c0c49e4de1d78497d13a7dbb360");
        dataMap.put("format","json");
        dataMap.put("nojsoncallback","1");

        Call<ApiResponce> call= getDataApi.getdata(dataMap);
        call.enqueue(new Callback<ApiResponce>() {
            @Override
            public void onResponse(Call<ApiResponce> call, Response<ApiResponce> response) {
               // Toast.makeText(MainActivity.this,"Test"+response.body(),Toast.LENGTH_LONG).show();
               // data.add(response.body().getPhotos().photo);
                generateDataList(response.body());

            }

            @Override
            public void onFailure(Call<ApiResponce> call, Throwable t) {
                Toast.makeText(MainActivity.this,"failed"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
    private void SearchData(int a,String s) {
        GetDataApi getDataApi=RetrofitClient.getRetrofit().create(GetDataApi.class);

        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("text",searchText);
        dataMap.put("page",""+a+"");
        dataMap.put("per_page","20");
        dataMap.put("method","flickr.photos.search");
        dataMap.put("api_key","062a6c0c49e4de1d78497d13a7dbb360");
        dataMap.put("format","json");
        dataMap.put("nojsoncallback","1");

        Call<ApiResponce> call= getDataApi.getdata(dataMap);
        call.enqueue(new Callback<ApiResponce>() {
            @Override
            public void onResponse(Call<ApiResponce> call, Response<ApiResponce> response) {
              //  Toast.makeText(MainActivity.this,"Test"+response.body(),Toast.LENGTH_LONG).show();
                // data.add(response.body().getPhotos().photo);
                searcheDataList(response.body());

            }

            @Override
            public void onFailure(Call<ApiResponce> call, Throwable t) {
                Toast.makeText(MainActivity.this,"failed"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }


    private void generateDataList(ApiResponce body) {

        //customAdapter.refreshData(body.getPhotos().photo);
        customAdapter.addItems(body.getPhotos().photo);

    }
    private void searcheDataList(ApiResponce body) {

        customAdapter.refreshData(body.getPhotos().photo);
        customAdapter.addItems(body.getPhotos().photo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item= menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(newText.isEmpty())
                {
                    getData(page,searchText);
                }
                else {
                    searchText=newText;
                    SearchData(page, searchText);
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


}
