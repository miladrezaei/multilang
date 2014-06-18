package com.cybermilad.multilang;

import java.util.Locale;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity {
	
	Spinner changeLangSpinner;
	Button refreshButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		loadLocale();
		
		changeLangSpinner = (Spinner) findViewById(R.id.change_lang_spinner);
		refreshButton = (Button) findViewById(R.id.refresh_button);
		
		changeLangSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				switch( position ){
					case 0:
						setLocale("en");	
					break;
					case 1:
						setLocale("de");
					break;
					case 2:
						setLocale("fa");
					break;
					default:
				    break;
				}				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		refreshButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MainActivity.this, MainActivity.class);
				startActivity(intent);
				
			}
		});
		
	}

	// set local
	public void setLocale(String lang) { 	
		
		SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("lang", lang);
		editor.commit(); 
	} 
	
	// load local after start activity
	public void loadLocale() {
		SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
		String selectedLang = sharedPreferences.getString("lang", "en");
		changeLocale( selectedLang );
	} 
	
	// change local
	public void changeLocale(String selectedLang) {
		Locale locale = new Locale( selectedLang ); 
	    Configuration config = new Configuration();
	    config.locale = locale;
	    getBaseContext().getResources().updateConfiguration(config, 
	    getBaseContext().getResources().getDisplayMetrics());
	    this.setContentView(R.layout.activity_main);
	} 	
	
}
