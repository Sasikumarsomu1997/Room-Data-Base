package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabase.model.Contacts;
import com.example.roomdatabase.viewmodel.ContactViewmodel;

public class UpdateContactActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private String originalName;
    private String originalEmail, existingContactId;
    private ContactViewmodel contactViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        contactViewmodel = new ViewModelProvider(this).get(ContactViewmodel.class);
        nameEditText = findViewById(R.id.editTextName);
        emailEditText = findViewById(R.id.editTextEmail);
        if (getIntent().getExtras() != null) {
            originalName = getIntent().getStringExtra("CONTACT_NAME");
            originalEmail = getIntent().getStringExtra("CONTACT_EMAIL");
            existingContactId = String.valueOf(getIntent().getIntExtra("CONTACT_ID", -1));
            nameEditText.setText(originalName);
            emailEditText.setText(originalEmail);
        }
        findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact();
            }
        });

    }
    private void updateContact() {
        String updatedName = nameEditText.getText().toString().trim();
        String updatedEmail = emailEditText.getText().toString().trim();
        if (updatedName.isEmpty() || updatedEmail.isEmpty()) {
            return;
        }
        Contacts updatedContact = new Contacts(updatedName, updatedEmail);
        updatedContact.setId(Integer.parseInt(existingContactId));
        if (contactViewmodel != null) {
            contactViewmodel.updateContact(updatedContact);
        } else {
            Toast.makeText(this, "ViewModel is null", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
