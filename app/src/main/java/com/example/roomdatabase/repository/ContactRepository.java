package com.example.roomdatabase.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.roomdatabase.dao.ContactDao;
import com.example.roomdatabase.database.ContactDatabase;
import com.example.roomdatabase.model.Contacts;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.logging.LogRecord;

public class ContactRepository {
    private final ContactDao contactDao;

    private final ExecutorService executorService;
    private final Handler handler;
    public ContactRepository(Application application){
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDao = contactDatabase.getContactDao();

        executorService= Executors.newSingleThreadExecutor();

        handler = new Handler(Looper.getMainLooper());

    }
    public void addContact(Contacts contacts){
        executorService.execute(() ->{
            contactDao.insert(contacts);

        });

    }
    public void deleteContact(Contacts contacts){
        executorService.execute(() ->{
            contactDao.delete(contacts);

        });

    }
    public void updateContact(Contacts contacts){
        executorService.execute(() ->{
            contactDao.update(contacts);

        });

    }
    public LiveData<List<Contacts>> getAllContacts(){
        return contactDao.getAllContacts();
    }


}
