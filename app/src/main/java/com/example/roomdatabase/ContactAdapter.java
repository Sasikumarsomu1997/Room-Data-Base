package com.example.roomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.databinding.ContactListItemBinding;
import com.example.roomdatabase.model.Contacts;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private ArrayList<Contacts>contactsArrayList;

    public ContactAdapter(ArrayList<Contacts> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
    }
    private OnContactClickListener onContactClickListener;
    public void setOnContactClickListener(OnContactClickListener onContactClickListener) {
        this.onContactClickListener = onContactClickListener;
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactListItemBinding contactListItemBinding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()),
                R.layout.contact_list_item,parent,false);
        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, int position) {
        Contacts contacts = contactsArrayList.get(position);
        holder.contactListItemBinding.setContact(contacts);
        holder.itemView.setOnClickListener(v -> {
            if (onContactClickListener != null) {
                onContactClickListener.onContactClick(contacts);
            }

                }
                );

    }

    @Override
    public int getItemCount() {
        return contactsArrayList.size();

    }
    public void setContacts(ArrayList<Contacts>contacts){
        this.contactsArrayList =contacts;
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{
        private ContactListItemBinding contactListItemBinding;

        public ContactViewHolder(@NonNull ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding =contactListItemBinding;
        }
    }
}
