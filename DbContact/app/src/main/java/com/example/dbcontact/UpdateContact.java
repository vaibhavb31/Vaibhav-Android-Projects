package com.example.dbcontact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dbcontact.Model.Contacts;
import com.example.dbcontact.data.MyDbHandler;
import com.example.dbcontact.databinding.DeleteContactBinding;
import com.example.dbcontact.databinding.UpdatecontactTileBinding;


public class UpdateContact extends AppCompatActivity {

    private UpdatecontactTileBinding updatecontactTileBinding;
    private DeleteContactBinding deleteContactBinding;
    EditText Name;
    EditText Phone;
    int UpdatedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updatecontactTileBinding= UpdatecontactTileBinding.inflate(getLayoutInflater());
        View view =updatecontactTileBinding.getRoot();
        setContentView(view);
        Name= updatecontactTileBinding.name;
        Phone= updatecontactTileBinding.phone;

        Intent intent= getIntent();
        String nametxt= intent.getStringExtra("name");
        String phonetxt= intent.getStringExtra("phone");
        int idtxt= intent.getIntExtra("id",1);

        Name.setText(nametxt);
        Phone.setText(phonetxt);


        MyDbHandler db= new MyDbHandler(this);

        Button btnSave= updatecontactTileBinding.save;

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Name.length()==0)
                {
                    updatecontactTileBinding.nameLay.setError("Enter Name");

                }
                else if(Phone.length()==0)
                {
                    updatecontactTileBinding.phoneLay.setError("Enter Phone Number");

                }
                else {

                    String name = updatecontactTileBinding.name.getText().toString();

                    String phone = updatecontactTileBinding.phone.getText().toString();
                    //Toast.makeText(addcontactTileBinding.getRoot().getContext(),"phone is "+phone,Toast.LENGTH_LONG).show();

                    Contacts contacts = new Contacts(idtxt, name, phone);


                    UpdatedId = db.updateContacts(contacts);


                    Toast.makeText(updatecontactTileBinding.getRoot().getContext(), "Contact Updated Sucesfully", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent();
                    intent.putExtra("updatedName", name);
                    intent.putExtra("updatedPhone", phone);
                    setResult(RESULT_OK, intent);
                    finish();
                }



            }
        });

        Button cancel= updatecontactTileBinding.btncancl;
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
