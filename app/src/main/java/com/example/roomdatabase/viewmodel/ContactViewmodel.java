package com.example.roomdatabase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdatabase.model.Contacts;
import com.example.roomdatabase.repository.ContactRepository;

import java.util.List;

public class ContactViewmodel extends AndroidViewModel {
    private ContactRepository contactRepository;
    public  LiveData<List<Contacts>> allContact;

    public ContactViewmodel(@NonNull Application application) {
        super(application);
        this.contactRepository = new ContactRepository(application);

    }
    public LiveData<List<Contacts>> getAllContact(){
        allContact = contactRepository.getAllContacts();
        return allContact;
    }
    public void addNewContact(Contacts contacts){contactRepository.addContact(contacts);}
    public void deleteContact(Contacts contacts){contactRepository.deleteContact(contacts);}
    public void updateContact(Contacts contacts){contactRepository.updateContact(contacts);}

}
