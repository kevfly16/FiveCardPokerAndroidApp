package com.example.fivecardpokerandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends Activity {

	Button backButton;
	TextView descriptionTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);

		descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
		descriptionTextView
				.setText("At the beginning of the game you must place a bid and then press continue. "
						+ "After placing a bid and pressing continue, your cards will be revealed, and you may"
						+ "choose which ones to hold by placing checks and then clicking hold after having chosen"
						+ "all cards that you would like to hold. The winner is then determined by comparing the"
						+ "value of the hands as determined by the rules of Poker. In order to begin the next round"
						+ "you must press continue and proceed in the same way as mentioned above.");
		backButton = (Button) findViewById(R.id.backButton);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				back();

			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}

	public void back() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

}
