package com.example.fivecardpokerandroid;

import java.util.Scanner;

public class Poker {
	int[][] previousCards;
	int noCards;
	int[][] playerHand;
	int[][] bankerHand;
	int fullHouseChooser;

	public Poker() {
		previousCards = new int[20][1];
		noCards = 0;
		playerHand = new int[5][1];
		bankerHand = new int[5][1];
		playerHand = generateHand(previousCards, noCards, 0, playerHand);
		// Tests the compare Method
		// setHand();
		bankerHand = generateHand(previousCards, noCards, 0, bankerHand);
	}

	public int[][] generateHand(int[][] previous, int noCards, int j,
			int[][] cards) {
		boolean duplicate = false;
		if (j == 5) {
			this.previousCards = previous;
			return cards;
		} else {

			do {
				duplicate = false;

				cards[j][0] = (int) (Math.random() * 52 + 1);

				for (int i = 0; i < noCards; i++) {
					if (cards[j][0] == previousCards[i][0])
						duplicate = true;
				}
			} while (duplicate);

			previous[noCards][0] = cards[j][0];
			this.noCards++;
			generateHand(previous, this.noCards, j + 1, cards);
			return cards;
		}
	}

	public int generateCard(int[][] previous, int noCards) {
		boolean duplicate = false;
		int card[] = new int[1];
		do {
			duplicate = false;

			card[0] = (int) (Math.random() * 52 + 1);

			for (int i = 0; i < noCards; i++) {
				if (card[0] == previousCards[i][0])
					duplicate = true;
			}
		} while (duplicate);

		previousCards[noCards][0] = card[0];
		this.noCards++;
		return card[0];
	}

	public int compare() {
		int numberPairPlayer = 53;
		int numberPairBanker = 53;
		int numberTriplePlayer = 53;
		int numberTripleBanker = 53;
		int numberFullHousePlayer = 53;
		int numberFullHouseBanker = 53;
		int numberStraightPlayer = 53;
		int numberStraightBanker = 53;
		int numberFlushPlayer = 53;
		int numberFlushBanker = 53;
		int numberBombPlayer = 53;
		int numberBombBanker = 53;
		boolean nothingPlayer = true;
		boolean nothingBanker = true;
		boolean pairPlayer = false;
		boolean pairBanker = false;
		boolean doublePairPlayer = false;
		boolean doublePairBanker = false;
		boolean triplePlayer = false;
		boolean tripleBanker = false;
		boolean straightPlayer = false;
		boolean straightBanker = false;
		boolean flushPlayer = false;
		boolean flushBanker = false;
		boolean fullHousePlayer = false;
		boolean fullHouseBanker = false;
		boolean bombPlayer = false;
		boolean bombBanker = false;
		boolean straightFlushPlayer = false;
		boolean straightFlushBanker = false;

		// pairs Player
		for (int i = 1; i < 52; i += 4)
			if (pair(playerHand, i)) {
				numberPairPlayer = i;
				pairPlayer = true;
				nothingPlayer = false;
				break;
			}

		// pairs Banker
		for (int i = 1; i < 52; i += 4)
			if (pair(bankerHand, i)) {
				numberPairBanker = i;
				pairBanker = true;
				nothingBanker = false;
				break;
			}

		// double Pairs Player
		for (int i = 1; i < 52; i += 4) {
			if (doublePairPlayer)
				break;
			for (int j = i + 4; j < 52; j += 4)
				if (doublePair(playerHand, i, j)) {
					numberPairPlayer = i;
					doublePairPlayer = true;
					pairPlayer = false;
					nothingPlayer = false;
					break;
				}
		}

		// double Pairs Banker
		for (int i = 1; i < 52; i += 4) {
			if (doublePairBanker)
				break;
			for (int j = i + 4; j < 52; j += 4)
				if (doublePair(bankerHand, i, j)) {
					numberPairBanker = i;
					doublePairBanker = true;
					pairBanker = false;
					nothingBanker = false;
					break;
				}
		}

		// triple Player
		for (int i = 1; i < 52; i++)
			if (triple(playerHand, i)) {
				numberTriplePlayer = i;
				triplePlayer = true;
				pairPlayer = false;
				nothingPlayer = false;
				break;
			}

		// triple Banker
		for (int i = 1; i < 52; i++)
			if (triple(bankerHand, i)) {
				numberTripleBanker = i;
				tripleBanker = true;
				pairBanker = false;
				nothingBanker = false;
				break;
			}

		// full House Player
		for (int i = 1; i < 52; i += 4) {
			if (fullHousePlayer)
				break;
			for (int j = i + 4; j < 52; j += 4)
				if (fullHouse(playerHand, i, j)) {
					if (fullHouseChooser == 1)
						numberFullHousePlayer = i;
					else
						numberFullHousePlayer = j;
					fullHousePlayer = true;
					triplePlayer = false;
					pairPlayer = false;
					nothingPlayer = false;
					break;
				}
		}

		// full House Banker
		for (int i = 1; i < 52; i += 4) {
			if (fullHouseBanker)
				break;
			for (int j = i + 4; j < 52; j += 4)
				if (fullHouse(bankerHand, i, j)) {
					if (fullHouseChooser == 1)
						numberFullHouseBanker = i;
					else
						numberFullHouseBanker = j;
					fullHouseBanker = true;
					tripleBanker = false;
					pairBanker = false;
					nothingBanker = false;
					break;
				}
		}

		// straight Player
		for (int i = 1; i < 52; i += 4)
			if (straight(playerHand, i)) {
				numberStraightPlayer = i;
				straightPlayer = true;
				nothingPlayer = false;
				break;
			}

		// straight Banker
		for (int i = 1; i < 52; i += 4)
			if (straight(bankerHand, i)) {
				numberStraightBanker = i;
				straightBanker = true;
				nothingBanker = false;
				break;
			}

		// flush Player
		if (flush(playerHand)) {
			numberFlushPlayer = playerHand[0][0];
			flushPlayer = true;
			nothingPlayer = false;
		}

		// flush Banker
		if (flush(bankerHand)) {
			numberFlushBanker = playerHand[0][0];
			flushBanker = true;
			nothingBanker = false;
		}

		// bomb Player
		for (int i = 1; i < 52; i++)
			if (bomb(playerHand, i)) {
				numberBombPlayer = i;
				bombPlayer = true;
				pairPlayer = false;
				triplePlayer = false;
				nothingPlayer = false;
				break;
			}

		// bomb Banker
		for (int i = 1; i < 52; i++)
			if (bomb(bankerHand, i)) {
				numberBombBanker = i;
				bombBanker = true;
				pairBanker = false;
				tripleBanker = false;
				nothingBanker = false;
				break;
			}

		if (nothingBanker && nothingPlayer)
			return nothing(playerHand, bankerHand);
		else if (nothingBanker && !nothingPlayer)
			return 1;
		else if (nothingPlayer && !nothingBanker)
			return 2;
		else if (pairPlayer && pairBanker && !doublePairPlayer
				&& !doublePairBanker && !fullHousePlayer && !fullHouseBanker
				&& !triplePlayer && !tripleBanker && !bombPlayer && !bombBanker)
			return (numberPairPlayer < numberPairBanker) ? 1 : 2;
		else if (pairPlayer && !doublePairPlayer && !fullHousePlayer
				&& !pairBanker && !triplePlayer && !bombPlayer
				&& !nothingBanker)
			return 2;
		else if (pairBanker && !doublePairBanker && !fullHouseBanker
				&& !pairPlayer && !tripleBanker && !bombBanker
				&& !nothingPlayer)
			return 1;
		else if (doublePairPlayer && doublePairBanker && !bombPlayer
				&& !bombBanker && !fullHousePlayer && !fullHouseBanker)
			return (numberPairPlayer < numberPairBanker) ? 1 : 2;
		else if (doublePairPlayer && !pairBanker && !fullHousePlayer
				&& !nothingBanker)
			return 2;
		else if (doublePairBanker && !pairPlayer && !fullHouseBanker
				&& !nothingPlayer)
			return 1;
		else if (triplePlayer && tripleBanker && !fullHousePlayer
				&& !fullHouseBanker && !bombPlayer && !bombBanker)
			return (numberTriplePlayer < numberTripleBanker) ? 1 : 2;
		else if (triplePlayer && !pairBanker && !fullHousePlayer && !bombPlayer
				&& !doublePairBanker && !nothingBanker)
			return 2;
		else if (tripleBanker && !pairPlayer && !fullHouseBanker && !bombBanker
				&& !doublePairPlayer && !nothingPlayer)
			return 1;
		else if (straightPlayer && straightBanker)
			return (numberStraightPlayer < numberStraightBanker) ? 1 : 2;
		else if (straightPlayer && (flushBanker || bombBanker))
			return 2;
		else if (straightBanker && (flushPlayer || bombPlayer))
			return 1;
		else if (flushPlayer && flushBanker)
			return suit(playerHand, bankerHand);
		else if (flushPlayer
				&& ((straightBanker && flushBanker) || bombBanker || fullHouseBanker))
			return 2;
		else if (flushBanker
				&& ((straightPlayer && flushPlayer) || bombPlayer || fullHousePlayer))
			return 1;
		else if (fullHousePlayer && fullHouseBanker)
			return (numberFullHousePlayer < numberFullHouseBanker) ? 1 : 2;
		else if (fullHousePlayer
				&& (!(straightBanker && flushBanker) || !bombBanker))
			return 1;
		else if (fullHouseBanker
				&& (!(straightPlayer && flushPlayer) || !bombPlayer))
			return 2;
		else if ((straightPlayer && flushPlayer)
				&& (straightBanker && flushBanker))
			return suit(playerHand, bankerHand);
		else if (bombPlayer && bombBanker)
			return (numberBombPlayer < numberBombBanker) ? 1 : 2;
		else if (bombBanker && !bombPlayer && !(straightPlayer && flushPlayer))
			return 2;
		else if (bombPlayer && !bombBanker && !(straightBanker && flushBanker))
			return 1;
		else
			return 0;
	}

	public boolean pair(int[][] hand, int card) {
		int count = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i][0] >= card && hand[i][0] < (card + 4)) {
				count++;
			}
		}

		return (count == 2) ? true : false;
	}

	public boolean doublePair(int[][] hand, int card, int card2) {
		int count = 0;
		int counter = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i][0] >= card && hand[i][0] < (card + 4)) {
				count++;
			}
			if (hand[i][0] >= card2 && hand[i][0] < (card2 + 4)) {
				counter++;
			}
		}

		return ((count == 2) && (counter == 2)) ? true : false;
	}

	public boolean triple(int[][] hand, int card) {
		int count = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i][0] >= card && hand[i][0] < (card + 4)) {
				count++;
			}
		}

		return (count == 3) ? true : false;
	}

	public boolean bomb(int[][] hand, int card) {
		int count = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i][0] >= card && hand[i][0] < (card + 4)) {
				count++;
			}
		}

		return (count == 4) ? true : false;
	}

	public boolean fullHouse(int[][] hand, int card, int card2) {
		int count = 0;
		int counter = 0;
		for (int i = 0; i < hand.length; i++) {
			if ((hand[i][0] >= card) && (hand[i][0] < (card + 4))) {
				count++;
			}
			if ((hand[i][0] >= card2) && (hand[i][0] < (card2 + 4))) {
				counter++;
			}
		}

		if (count == 3) {
			fullHouseChooser = 1;
		}
		if (counter == 3) {
			fullHouseChooser = 2;
		}

		if ((count == 2 && counter == 3) || (count == 3 && counter == 2))
			return true;
		else
			return false;
	}

	public boolean straight(int[][] hand, int card) {
		for (int i = 0; i < hand.length; i++) {
			if (hand[i][0] >= card && hand[i][0] <= (card + 16))
				;
			else
				return false;

		}

		return true;
	}

	public boolean flush(int[][] hand) {
		for (int i = 0; i + 1 < hand.length; i++) {
			if ((hand[i][0] + hand[i + 1][0]) % 4 == 0)
				;
			else
				return false;
		}

		return true;
	}

	public int nothing(int[][] hand1, int[][] hand2) {
		int currentMaxPlayer = 53;
		int currentMaxBanker = 53;
		for (int i = 0; i < playerHand.length; i++) {
			if (playerHand[i][0] < currentMaxPlayer)
				currentMaxPlayer = playerHand[i][0];
			if (bankerHand[i][0] < currentMaxBanker)
				currentMaxBanker = bankerHand[i][0];
		}

		if (currentMaxPlayer < currentMaxBanker)
			return 1;
		else if (Math.abs(currentMaxPlayer - currentMaxBanker) < 4)
			return 0;
		else
			return 2;
	}

	public int suit(int[][] hand, int hand2[][]) {
		if ((hand[0][0] - 1) % 4 == 0) {
			if (hand2[0][0] % 4 == 0)
				return 1;
			else
				return 2;
		}

		else if ((hand[0][0] - 2) % 4 == 0)
			return 1;
		else if ((hand[0][0] - 3) % 4 == 0) {
			if ((hand2[0][0] - 2) % 4 == 0)
				return 2;
			else
				return 1;
		} else
			return 2;
	}

	public void setHand() {
		Scanner input = new Scanner(System.in);
		playerHand[0][0] = input.nextInt();
		playerHand[1][0] = input.nextInt();
		playerHand[2][0] = input.nextInt();
		playerHand[3][0] = input.nextInt();
		playerHand[4][0] = input.nextInt();
	}
}
