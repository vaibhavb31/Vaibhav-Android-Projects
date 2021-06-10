package com.example.dbcontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.dbcontact.Model.Contacts;
import com.example.dbcontact.ViewHolder.CustomAdapter;
import com.example.dbcontact.data.MyDbHandler;
import com.example.dbcontact.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

private ActivityMainBinding activityMainBinding;
 CustomAdapter adapter;
private RecyclerView recyclerView;
    List<Contacts> allContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= ActivityMainBinding.inflate(getLayoutInflater());
        View view= activityMainBinding.getRoot();
        setContentView(view);

        initUi();


        MyDbHandler db= new MyDbHandler(MainActivity.this);

         allContacts=db.getContacts();
        if (allContacts==null) {
            initUi();
        }
        else
        {
            getContacts(allContacts);
        }


        ImageButton button= activityMainBinding.addcontact;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
        activityMainBinding.searchTxtchild.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }

            void filter(String text){
                List<Contacts> temp = new ArrayList();
                for(Contacts d: allContacts){
                    //or use .equal(text) with you want equal match
                    //use .toLowerCase() for better matches

                    if((d.getName().toLowerCase().contains(text.toLowerCase())) ||
                            (d.getPhoneNumber().toLowerCase().contains(text.toLowerCase())) ){
                        temp.add(d);
                    }
                }
                //update recyclerview
                adapter.updateList(temp);
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        MyDbHandler db= new MyDbHandler(MainActivity.this);
         allContacts=db.getContacts();
        getContacts(allContacts);

    }

    private void getContacts(List<Contacts> allContacts) {
        adapter.refreshList(allContacts);
    }

    private void initUi() {
        recyclerView= activityMainBinding.recycle;
        adapter= new CustomAdapter(new ArrayList<>());
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void openNewActivity(){
        Intent intent = new Intent(MainActivity.this, AddContacts.class);
        MainActivity.this.startActivity(intent);
    }


}