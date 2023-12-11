package com.example.roomdatabase.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabase.model.Contacts;

import java.util.List;
@Dao
public interface ContactDao {

    @Insert
    void insert(Contacts contacts);

    @Delete
    void delete(Contacts contacts);

    @Update
    void update(Contacts contacts);

    @Query("SELECT * FROM contacts_id")
    LiveData<List<Contacts>> getAllContacts();

}
