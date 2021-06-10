package com.example.dbcontact;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dbcontact.data.MyDbHandler;
import com.example.dbcontact.databinding.DeleteContactBinding;

public class DeleteContact extends AppCompatActivity {
    private DeleteContactBinding deleteContactBinding;
    TextView name;
    TextView phone;

    Button delete;
    Button edit;
    String nametxt;
    String phonetxt;
    String finalName;
    String finalphone;
    AlertDialog mDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteContactBinding = DeleteContactBinding.inflate(getLayoutInflater());
        View view = deleteContactBinding.getRoot();
        setContentView(view);
        name= deleteContactBinding.name;
        phone= deleteContactBinding.phone;
        delete= deleteContactBinding.del;
        edit= deleteContactBinding.edit;

        MyDbHandler db= new MyDbHandler(this);



        Intent intent= getIntent();
         nametxt= intent.getStringExtra("name");
         phonetxt= intent.getStringExtra("phone");
        int idtxt= intent.getIntExtra("id",1);
        finalName= nametxt;
        finalphone= phonetxt;

        name.setText(finalName);
        phone.setText(finalphone);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog dialog = new AlertDialog.Builder(deleteContactBinding.getRoot().getContext())
                        .setTitle("Message")
                        .setMessage("Are you sure to Delete this Contact ?")
                        .setPositiveButton("OK",null)
                        .setNegativeButton("CANCEL",null)
                        .show();
                Button postivieDel= dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                postivieDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.deleteContact(idtxt);
                        Toast.makeText(deleteContactBinding.getRoot().getContext(),"Contact Sucessfully Deleted",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        finish();

                    }
                });





            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent= new Intent(deleteContactBinding.getRoot().getContext(),UpdateContact.class);
               // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name",finalName);
                intent.putExtra("phone", finalphone);
                intent.putExtra("id",idtxt);
                startActivityForResult(intent, 1);

                //deleteContactBinding.getRoot().getContext().startActivity(intent);
            }
        });




    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
               finalName = data.getStringExtra("updatedName");
                 finalphone = data.getStringExtra("updatedPhone");

                name.setText(finalName);
                phone.setText(finalphone);


            }
        }
    }


}
