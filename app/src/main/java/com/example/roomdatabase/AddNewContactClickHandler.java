package com.example.roomdatabase;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.roomdatabase.model.Contacts;
import com.example.roomdatabase.view.MainActivity;
import com.example.roomdatabase.viewmodel.ContactViewmodel;

public class AddNewContactClickHandler {

    Contacts contacts;
    Context context;
    ContactViewmodel contactViewmodel;

    public AddNewContactClickHandler(Contacts contacts, Context context, ContactViewmodel contactViewmodel) {
        this.contacts = contacts;
        this.context = context;
        this.contactViewmodel = contactViewmodel;
    }
    public void onSubmitButtonClicked(View view){
        if (contacts.getName() == null || contacts.getEmail() == null){
            Toast.makeText(context, "Fields Can not be Empty", Toast.LENGTH_SHORT).show();

        }
        else {
            Intent intent = new Intent(context, MainActivity.class);
            Contacts c = new Contacts(contacts.getName(),contacts.getEmail());
            contactViewmodel.addNewContact(c);
            context.startActivity(intent);
        }
    }

}
