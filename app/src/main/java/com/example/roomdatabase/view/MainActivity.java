package com.example.roomdatabase.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.roomdatabase.ContactAdapter;
import com.example.roomdatabase.MainActivityClickHandler;
import com.example.roomdatabase.OnContactClickListener;
import com.example.roomdatabase.R;
import com.example.roomdatabase.UpdateContactActivity;
import com.example.roomdatabase.database.ContactDatabase;
import com.example.roomdatabase.databinding.ActivityMainBinding;
import com.example.roomdatabase.model.Contacts;
import com.example.roomdatabase.viewmodel.ContactViewmodel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnContactClickListener {
    private ContactDatabase contactDatabase;
    private ActivityMainBinding activityMainBinding;
    private ContactAdapter contactAdapter;
    private MainActivityClickHandler handlers;
    private ContactViewmodel contactViewmodel;
    private  RecyclerView recyclerView;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        contactViewmodel = new ViewModelProvider(this).get(ContactViewmodel.class);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        contactAdapter = new ContactAdapter(contactsArrayList);
        contactAdapter.setOnContactClickListener(this);
        recyclerView = activityMainBinding.recyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactAdapter);
        handlers = new MainActivityClickHandler(this);
        activityMainBinding.setClickHandler(handlers);

        contactDatabase = ContactDatabase.getInstance(this);
        contactViewmodel = new ViewModelProvider(this).get(ContactViewmodel.class);
        contactViewmodel.getAllContact().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {
                contactsArrayList.clear();
                contactsArrayList.addAll(contacts);
                contactAdapter.setContacts((ArrayList<Contacts>) contacts);
                contactAdapter.notifyDataSetChanged();

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Contacts c = contactsArrayList.get(viewHolder.getAdapterPosition());

                contactViewmodel.deleteContact(c);

            }
        }).attachToRecyclerView(recyclerView);



    }

    @Override
    public void onContactClick(Contacts contacts) {
        openUpdateActivity(contacts);
    }
    private void openUpdateActivity(Contacts contact) {
        Intent intent = new Intent(this, UpdateContactActivity.class);
        intent.putExtra("CONTACT_ID", contact.getId()); // Pass the contact ID
        intent.putExtra("CONTACT_NAME", contact.getName());
        intent.putExtra("CONTACT_EMAIL", contact.getEmail());
        startActivity(intent);
    }

}
