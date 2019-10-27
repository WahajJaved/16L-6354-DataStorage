package com.klan.datastorage.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.klan.datastorage.Contact;
import com.klan.datastorage.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Contact.class}, version=1, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {
	public abstract ContactDAO getContactDAO();

	private static volatile ContactsDatabase INSTANCE;
	private static final int NUMBER_OF_THREADS = 4;
	static final ExecutorService databaseWriteExecutor =
			Executors.newFixedThreadPool(NUMBER_OF_THREADS);

	public static ContactsDatabase getDatabase(final Context context) {
		if (INSTANCE == null) {
			synchronized (ContactsDatabase.class) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
							ContactsDatabase.class, "contacts_database")
							.allowMainThreadQueries()
							.build();
				}
			}
		}
		return INSTANCE;
	}
}
