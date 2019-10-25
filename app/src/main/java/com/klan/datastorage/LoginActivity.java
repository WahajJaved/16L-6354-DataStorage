package com.klan.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {


	private String email, password;
	private Button loginButton;
	private EditText editTextEmail, editTextPassword;
	private Switch rememberMeSwitch;
	private SharedPreferences loginPreferences;
	private SharedPreferences.Editor loginPreferencesEditor;
	private Boolean rememberMe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		loginButton = findViewById(R.id.login);

		editTextEmail = findViewById(R.id.email);
		editTextPassword = findViewById(R.id.password);

		rememberMeSwitch = findViewById(R.id.rememberMe);

		loginPreferences = getPreferences(MODE_PRIVATE);
		loginPreferencesEditor = loginPreferences.edit();

		rememberMe = loginPreferences.getBoolean("rememberMe", false);

		if (rememberMe) {
			editTextEmail.setText(loginPreferences.getString("email", ""));
			editTextPassword.setText(loginPreferences.getString("password",""));
			rememberMeSwitch.setChecked(true);

			email = editTextEmail.getText().toString();
			password = editTextPassword.getText().toString();

			authenticate(email, password);
		}

	}

	public void login(View view) {
		if (view == loginButton) {

			email = editTextEmail.getText().toString();
			password = editTextPassword.getText().toString();

			if (rememberMeSwitch.isChecked()) {
				loginPreferencesEditor.putBoolean("rememberMe", true);
				loginPreferencesEditor.putString("email", email);
				loginPreferencesEditor.putString("password", password);
				loginPreferencesEditor.commit();
			}
			else {
				loginPreferencesEditor.clear();
				loginPreferencesEditor.commit();
			}

			authenticate(email, password);



		}
	}
	private void authenticate(String username, String password) {
		if (username == "wj" && password == "1248"){
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
			LoginActivity.this.finish();
		}
		else{
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
			LoginActivity.this.finish();
			return;
		}
	}
//
//	public void register(View view) {
//		Intent intent = new Intent(this, RegisterActivity.class);
//		startActivity(intent);
//	}
//
//	public void reset_password(View view) {
//		Intent intent = new Intent(this, ResetPasswordActivity.class);
//		startActivity(intent);
//	}
}
