package com.klan.datastorage.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.klan.datastorage.Contact;

import java.util.List;

@Dao
public interface ContactDAO {

	@Query("SELECT * FROM contacts")
	List<Contact> getAll();

	@Query("DELETE FROM contacts")
	void clearContacts();

	@Query("DELETE FROM contacts where contacts.id=:identifier")
	void deleteContact(int identifier);

	@Insert
	void insert(Contact contact);
}
