package com.klan.datastorage.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.klan.datastorage.Contact;
import com.klan.datastorage.HomeActivity;
import com.klan.datastorage.R;
import com.klan.datastorage.ContactAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

	FloatingActionButton addContactButton;


	public HomeFragment() {
		// Required empty public constructor
	}

	// TODO: Rename and change types and number of parameters
	public static HomeFragment newInstance() {
		HomeFragment fragment = new HomeFragment();
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

		List<Contact> contacts = HomeActivity.database.getContactDAO().getAll();


		View view = inflater.inflate(R.layout.fragment_home, container, false);
		RecyclerView contactsRecyclerView = view.findViewById(R.id.contactRecyclerView);
		ContactAdapter adapter = new ContactAdapter(contacts);
		contactsRecyclerView.setAdapter(adapter);
		contactsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		addContactButton = view.findViewById(R.id.add_contact_btn);
		addContactButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AddContactFragment addContactFragment = new AddContactFragment();
				HomeActivity.fragmentManager.beginTransaction().replace(R.id.framgent_container, addContactFragment).addToBackStack(null).commit();
			}
		});

		DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
		contactsRecyclerView.addItemDecoration(decoration);

		return view;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}
}
