package com.example.dbcontact;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dbcontact.Model.Contacts;
import com.example.dbcontact.data.MyDbHandler;
import com.example.dbcontact.databinding.AddcontactTileBinding;

public class AddContacts extends AppCompatActivity {
    private AddcontactTileBinding addcontactTileBinding;
    EditText name, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addcontactTileBinding= AddcontactTileBinding.inflate(getLayoutInflater());
        View view =addcontactTileBinding.getRoot();
        setContentView(view);
        MyDbHandler db= new MyDbHandler(this);

        Button btnSave= addcontactTileBinding.save;
        name= addcontactTileBinding.name;
        phone= addcontactTileBinding.phone;




        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.length()==0)
                {
                    addcontactTileBinding.nameLay.setError("Enter Name");

                    //Toast.makeText(addcontactTileBinding.getRoot().getContext(),"Name cannot be empty",Toast.LENGTH_LONG).show();

                }
                else if(phone.length()==0)
                {
                    addcontactTileBinding.phoneLay.setError("Enter Phone Number");
                   // Toast.makeText(addcontactTileBinding.getRoot().getContext(),"Phone Number cannot be empty",Toast.LENGTH_LONG).show();

                }
                else {
                    String nametxt= name.getText().toString();
                    String phntxt= phone.getText().toString();
                    Contacts contacts = new Contacts(nametxt, phntxt);
                    db.addContact(contacts);
                    Toast.makeText(addcontactTileBinding.getRoot().getContext(), "Contact Added Sucesfully", Toast.LENGTH_LONG).show();

                    finish();
                }


            }
        });

    }
}
