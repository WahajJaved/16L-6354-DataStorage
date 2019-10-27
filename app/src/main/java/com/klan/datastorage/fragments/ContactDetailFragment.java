package com.klan.datastorage.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.klan.datastorage.Contact;
import com.klan.datastorage.HomeActivity;
import com.klan.datastorage.R;
import com.klan.datastorage.db.ContactRepository;


public class ContactDetailFragment extends Fragment {

	ImageView ContactImage;
	TextView ContactName;
	TextView ContactNumber;
	TextView ContactEmail;
	Button deleteContact;

	public ContactDetailFragment() {
		// Required empty public constructor
	}


	public static ContactDetailFragment newInstance() {
		ContactDetailFragment fragment = new ContactDetailFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
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
		View view = inflater.inflate(R.layout.fragment_contact_detail, container, false);
		ContactName = view.findViewById(R.id.contact_name);
		ContactNumber = view.findViewById(R.id.contact_phone);
		ContactEmail = view.findViewById(R.id.contact_email);
		ContactImage = view.findViewById(R.id.contact_image);

		final int contactId = getArguments().getInt("Id");

		ContactName.setText(getArguments().getString("Name"));
		ContactEmail.setText(getArguments().getString("Email"));
		ContactNumber.setText(getArguments().getString("Number"));
		ContactImage.setImageURI(Uri.parse(getArguments().getString("Image")));
		deleteContact = view.findViewById(R.id.delete_button);



		deleteContact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ContactRepository.deleteContact(contactId);
				Toast.makeText(getActivity(), "Contact has been removed successfully", Toast.LENGTH_SHORT).show();
				HomeActivity.fragmentManager.beginTransaction().replace(R.id.framgent_container, new HomeFragment(),"deleteContact").commit();
			}
		});

		return view;
	}
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}
}
