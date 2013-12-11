package com.example.fivecardpokerandroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class GameActivity extends Activity {

	ImageView imageView1;
	ImageView imageView2;
	ImageView imageView3;
	ImageView imageView4;
	ImageView imageView5;
	ImageView imageView6;
	ImageView imageView7;
	ImageView imageView8;
	ImageView imageView9;
	ImageView imageView10;

	CheckBox card1;
	CheckBox card2;
	CheckBox card3;
	CheckBox card4;
	CheckBox card5;

	TextView valueOfBid;
	TextView outcomeTextView;
	TextView purseTextView;

	SeekBar bidSeekBar;

	Button holdButton;
	Button continueButton;

	int bid = 0;
	int purse = 250;
	boolean reveal = false;
	boolean playerTurn = true;
	boolean newRound = false;
	Poker game;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		imageView3 = (ImageView) findViewById(R.id.imageView3);
		imageView4 = (ImageView) findViewById(R.id.imageView4);
		imageView5 = (ImageView) findViewById(R.id.imageView5);
		imageView6 = (ImageView) findViewById(R.id.imageView6);
		imageView7 = (ImageView) findViewById(R.id.imageView7);
		imageView8 = (ImageView) findViewById(R.id.imageView8);
		imageView9 = (ImageView) findViewById(R.id.imageView9);
		imageView10 = (ImageView) findViewById(R.id.imageView10);

		card1 = (CheckBox) findViewById(R.id.card1);
		card2 = (CheckBox) findViewById(R.id.card2);
		card3 = (CheckBox) findViewById(R.id.card3);
		card4 = (CheckBox) findViewById(R.id.card4);
		card5 = (CheckBox) findViewById(R.id.card5);

		valueOfBid = (TextView) findViewById(R.id.valueOfBid);

		outcomeTextView = (TextView) findViewById(R.id.outcomeTextView);
		purseTextView = (TextView) findViewById(R.id.purseTextView);

		bidSeekBar = (SeekBar) findViewById(R.id.bidSeekBar);
		bidSeekBar.setOnSeekBarChangeListener(bidSeekBarListener);

		holdButton = (Button) findViewById(R.id.holdButton);

		holdButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				hold();

			}

		});
		continueButton = (Button) findViewById(R.id.continueButton);

		continueButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				cont();

			}

		});

		game = new Poker();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	
	OnSeekBarChangeListener bidSeekBarListener = new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			bid = (bidSeekBar.getProgress()) * 5;
			valueOfBid.setText(bid + "");
		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {

		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {

		}

	};;

	public void hold() {
		if (reveal) {
			if (!card1.isChecked()) {
				game.playerHand[0][0] = game.generateCard(game.previousCards,
						game.noCards);

				String image6 = "c" + game.playerHand[0][0];

				int resId6 = getResources().getIdentifier(image6, "drawable",
						"com.example.fivecardpokerandroid");

				imageView6.setImageResource(resId6);
			}
			if (!card2.isChecked()) {
				game.playerHand[1][0] = game.generateCard(game.previousCards,
						game.noCards);

				String image7 = "c" + game.playerHand[1][0];

				int resId7 = getResources().getIdentifier(image7, "drawable",
						"com.example.fivecardpokerandroid");

				imageView7.setImageResource(resId7);
			}
			if (!card3.isChecked()) {
				game.playerHand[2][0] = game.generateCard(game.previousCards,
						game.noCards);

				String image8 = "c" + game.playerHand[2][0];

				int resId8 = getResources().getIdentifier(image8, "drawable",
						"com.example.fivecardpokerandroid");

				imageView8.setImageResource(resId8);
			}
			if (!card4.isChecked()) {
				game.playerHand[3][0] = game.generateCard(game.previousCards,
						game.noCards);

				String image9 = "c" + game.playerHand[3][0];

				int resId9 = getResources().getIdentifier(image9, "drawable",
						"com.example.fivecardpokerandroid");

				imageView9.setImageResource(resId9);
			}
			if (!card5.isChecked()) {
				game.playerHand[4][0] = game.generateCard(game.previousCards,
						game.noCards);

				String image10 = "c" + game.playerHand[4][0];

				int resId10 = getResources().getIdentifier(image10, "drawable",
						"com.example.fivecardpokerandroid");

				imageView10.setImageResource(resId10);
			}

			reveal = false;
			revealBanker();
		}
	}

	public void cont() {
		if (newRound) {
			removeCards();
			game = new Poker();
			playerTurn = true;
			newRound = false;
			outcomeTextView.setText("");
			card1.setChecked(false);
			card2.setChecked(false);
			card3.setChecked(false);
			card4.setChecked(false);
			card5.setChecked(false);
		} else if (!playerTurn) {
			Dialog dialog = new Dialog(this);
			dialog.setContentView(R.layout.popup);
			TextView popupMessage = (TextView)dialog.findViewById(R.id.popupMessage);
			popupMessage.setText("Please Select The Cards To Hold Before Pressing Continue.");
			dialog.show();
		} else if (bid == 0) {
			Dialog dialog = new Dialog(this);
			dialog.setContentView(R.layout.popup);
			TextView popupMessage = (TextView)dialog.findViewById(R.id.popupMessage);
			popupMessage.setText("Please Enter A Bid.");
			dialog.show();
		} else if(purse<bid){
			Dialog dialog = new Dialog(this);
			dialog.setContentView(R.layout.popup);
			TextView popupMessage = (TextView)dialog.findViewById(R.id.popupMessage);
			popupMessage.setText("You Do Not Have The Funds To Bet This Amount.");
			dialog.show();
		}  else {
			playerTurn = false;
			purse -= bid;
			purseTextView.setText("$" + purse);
			revealPlayer();
		}
	}

	public void revealPlayer() {
		String image6 = "c" + game.playerHand[0][0];
		String image7 = "c" + game.playerHand[1][0];
		String image8 = "c" + game.playerHand[2][0];
		String image9 = "c" + game.playerHand[3][0];
		String image10 = "c" + game.playerHand[4][0];

		int resId6 = getResources().getIdentifier(image6, "drawable",
				"com.example.fivecardpokerandroid");
		int resId7 = getResources().getIdentifier(image7, "drawable",
				"com.example.fivecardpokerandroid");
		int resId8 = getResources().getIdentifier(image8, "drawable",
				"com.example.fivecardpokerandroid");
		int resId9 = getResources().getIdentifier(image9, "drawable",
				"com.example.fivecardpokerandroid");
		int resId10 = getResources().getIdentifier(image10, "drawable",
				"com.example.fivecardpokerandroid");

		imageView6.setImageResource(resId6);
		imageView7.setImageResource(resId7);
		imageView8.setImageResource(resId8);
		imageView9.setImageResource(resId9);
		imageView10.setImageResource(resId10);

		reveal = true;
	}

	public void removeCards() {
		imageView1.setImageResource(R.drawable.b1fv);
		imageView2.setImageResource(R.drawable.b1fv);
		imageView3.setImageResource(R.drawable.b1fv);
		imageView4.setImageResource(R.drawable.b1fv);
		imageView5.setImageResource(R.drawable.b1fv);
		imageView6.setImageResource(R.drawable.b1fv);
		imageView7.setImageResource(R.drawable.b1fv);
		imageView8.setImageResource(R.drawable.b1fv);
		imageView9.setImageResource(R.drawable.b1fv);
		imageView10.setImageResource(R.drawable.b1fv);
	}

	public void revealBanker() {
		String image1 = "c" + game.bankerHand[0][0];
		String image2 = "c" + game.bankerHand[1][0];
		String image3 = "c" + game.bankerHand[2][0];
		String image4 = "c" + game.bankerHand[3][0];
		String image5 = "c" + game.bankerHand[4][0];

		int resId1 = getResources().getIdentifier(image1, "drawable",
				"com.example.fivecardpokerandroid");
		int resId2 = getResources().getIdentifier(image2, "drawable",
				"com.example.fivecardpokerandroid");
		int resId3 = getResources().getIdentifier(image3, "drawable",
				"com.example.fivecardpokerandroid");
		int resId4 = getResources().getIdentifier(image4, "drawable",
				"com.example.fivecardpokerandroid");
		int resId5 = getResources().getIdentifier(image5, "drawable",
				"com.example.fivecardpokerandroid");

		imageView1.setImageResource(resId1);
		imageView2.setImageResource(resId2);
		imageView3.setImageResource(resId3);
		imageView4.setImageResource(resId4);
		imageView5.setImageResource(resId5);

		if (game.compare() == 1) {
			purse += (bid * 2);
			outcomeTextView.setText("Player Wins.");
			purseTextView.setText("$" + purse);
		} else if (game.compare() == 2) {
			outcomeTextView.setText("Banker Wins.");
			purseTextView.setText("$" + purse);
		} else {
			purse += bid;
			outcomeTextView.setText("It's a Tie.");
			purseTextView.setText("$" + purse);
		}

		newRound = true;
	}
}
