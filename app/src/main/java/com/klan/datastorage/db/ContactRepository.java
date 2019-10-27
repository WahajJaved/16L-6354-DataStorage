package com.klan.datastorage.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import com.klan.datastorage.Contact;
import com.klan.datastorage.User;

import java.util.List;

public class ContactRepository {

	private static ContactDAO contactDAO;

	private List<Contact> contactList;

	public  ContactRepository(Context context) {
		ContactsDatabase contactsDatabase = ContactsDatabase.getDatabase(context);
		contactDAO = contactsDatabase.getContactDAO();
		contactList = contactDAO.getAll();
	}

	public List<Contact> getContacts() {
		return contactList;
	}

	public boolean authenticate(final String email, final String password) {
		return true;
	}
	public static void deleteContact(int uid) {
		contactDAO.deleteContact(uid);
	}
}
