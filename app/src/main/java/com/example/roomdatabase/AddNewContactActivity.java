package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.roomdatabase.databinding.ActivityAddNewContactBinding;
import com.example.roomdatabase.model.Contacts;
import com.example.roomdatabase.viewmodel.ContactViewmodel;

public class AddNewContactActivity extends AppCompatActivity {
    private ActivityAddNewContactBinding activityAddNewContactBinding;

    private AddNewContactClickHandler handler;
    private Contacts contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        contacts = new Contacts();
        activityAddNewContactBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_new_contact);

        ContactViewmodel viewmodel = new ViewModelProvider(this).get(ContactViewmodel.class);
        handler = new AddNewContactClickHandler(contacts,this,viewmodel);

        activityAddNewContactBinding.setContact(contacts);
        activityAddNewContactBinding.setClickHandler(handler);
    }
}