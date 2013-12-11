package com.example.fivecardpokerandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button newGameButton;
	Button helpButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		newGameButton = (Button) findViewById(R.id.backButton);
		newGameButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				newGame();
				
			}
			
		});
		
		helpButton = (Button) findViewById(R.id.helpButton);
		helpButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				help();
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void newGame(){
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
		
	}
	
	public void help(){
		Intent intent = new Intent(this, HelpActivity.class);
		startActivity(intent);
	}

}
