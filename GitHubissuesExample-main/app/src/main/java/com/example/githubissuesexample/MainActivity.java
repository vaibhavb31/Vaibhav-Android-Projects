package com.example.githubissuesexample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    CutomAdapter adapter;
    Call<List<Issues>> call;
    GetDataService service;
    String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Date time=  Calendar.getInstance().getTime();
        Log.d("time",time.toString());
        initUi() ;

        service = RetrofitClient.getRetrofit().create(GetDataService.class);

        if (getPreferenceObjectJson(this, "Issues") != null) {
            List<Issues> obj = getPreferenceObjectJson(this, "Issues");
            adapter.refreshList(obj);
            Log.d("tag", obj+"");
        } else {
            call = service.getAllIssue();
            getDataFromServer();
        }
    }

    private void initUi() {
        recyclerView = findViewById(R.id.recycle);
        adapter = new CutomAdapter(new ArrayList<>(), this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getDataFromServer() {
        call.enqueue(new Callback<List<Issues>>() {
            @Override
            public void onResponse(Call<List<Issues>> call, Response<List<Issues>> response) {
                //Toast.makeText(MainActivity.this,"hello inside issue",Toast.LENGTH_LONG).show();
                adapter.refreshList(response.body());
                // Create Gson object.
                Gson gson = new Gson();
                // Get java object list json format string.
                String userInfoListJsonString = gson.toJson(response.body());
                setPreferenceObject(MainActivity.this, response.body(), "Issues");
                Log.d("Issue", userInfoListJsonString);
            }

            @Override
            public void onFailure(Call<List<Issues>> call, Throwable t) {
            }
        });

    }

    static public void setPreferenceObject(Context c, List<Issues> modal, String key) {
        /** storing object in preferences  **/
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(
                c.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();

        Gson gson = new Gson();
        String jsonObject = gson.toJson(modal);
        prefsEditor.putString(key, jsonObject);
        prefsEditor.commit();

    }

    public List<Issues> getPreferenceObjectJson(Context c, String key) {

        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(
                c.getApplicationContext());
        /** get issues data    ***/
        String json = appSharedPrefs.getString(key, "");
        Gson gson = new Gson();

        Type type = new TypeToken<List<Issues>>() {
        }.getType();
        List<Issues> issueList = gson.fromJson(json, type);
        return issueList;
    }
}