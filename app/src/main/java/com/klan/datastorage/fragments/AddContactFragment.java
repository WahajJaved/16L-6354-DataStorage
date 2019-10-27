package com.klan.datastorage.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.klan.datastorage.Contact;
import com.klan.datastorage.HomeActivity;
import com.klan.datastorage.R;
import com.klan.datastorage.db.ContactsDatabase;


public class AddContactFragment extends Fragment {

	TextView Name;
	TextView Email;
	TextView Phone;
	Button AddButton;

	public AddContactFragment() {
		// Required empty public constructor
	}

	public static AddContactFragment newInstance() {
		AddContactFragment fragment = new AddContactFragment();

		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		final ContactsDatabase database = ContactsDatabase.getDatabase(getContext());

		View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
		Name = view.findViewById(R.id.name_input);
		Email = view.findViewById(R.id.email_input);
		Phone = view.findViewById(R.id.phone_input);
		AddButton = view.findViewById(R.id.add_contact_button);

		AddButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Contact contact = new Contact();
				contact.setPhone(Phone.getText().toString());
				contact.setEmail(Email.getText().toString());
				contact.setName(Name.getText().toString());

				if (Phone.getText().toString().equals("") || Phone.getText().toString().equals(""))
					Toast.makeText(getContext(),"Enter Valid Details", Toast.LENGTH_SHORT).show();
				else {
					database.getContactDAO().insert(contact);
					HomeFragment frag = new HomeFragment();
					HomeActivity.fragmentManager.beginTransaction().replace(R.id.framgent_container, frag).commit();
				}
			}
		});
		return view;
	}



	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}


	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}
}
