package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Game {

	boolean isGettingOutOfPenaltyBox;

	GameSet gameSet = new GameSet();

	public void roll(int diceRoll) {
		System.out.println(playerName() + " is the current player");
		System.out.println("They have rolled a " + diceRoll);

		if (gameSet.isInPenaltyBox()) {
			if (diceRoll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				gameSet.releasePlayerFromThePenaltyBox();
				System.out.println(playerName() + " is getting out of the penalty box");
			} else {
				isGettingOutOfPenaltyBox = false;
				System.out.println(playerName() + " is not getting out of the penalty box");
			}
		}

		if (!gameSet.isInPenaltyBox()) {
			moveAndAskQuestion(diceRoll);
		}

	}

	public void moveAndAskQuestion(int diceRoll) {
		gameSet.movePlayer(diceRoll);

		System.out.println(playerName() + "'s new location is " + gameSet.getPlaces()[gameSet.getCurrentPlayer()]);
		System.out.println("The category is " + gameSet.currentCategory());
		gameSet.askQuestion(this);
	}

	public boolean answerCorrectly() {
		if (gameSet.isInPenaltyBox() && isGettingOutOfPenaltyBox) {
				gameSet.releasePlayerFromThePenaltyBox();
		}
		
		if (!gameSet.isInPenaltyBox()) {
			System.out.println("Answer is correct !!!!");
			gameSet.giveACoinToThePlayer();
			System.out.println(playerName() + " now has " + gameSet.getPlayerCoins() + " Gold Coins.");
		}
		gameSet.getNextPlayer();
		
		return didPlayerWin();
	}

	public boolean answerWrongly() {
		System.out.println("Question was incorrectly answered");
		System.out.println(playerName() + " was sent to the penalty box");
		gameSet.moveToPenaltyBox();

		gameSet.getNextPlayer();
		return true;
	}

	public boolean isPlayable() {
		return (countPlayers() >= 2);
	}

	public boolean add(String playerName) {
		return gameSet.addPlayer(this, playerName);
	}

	public int countPlayers() {
		return gameSet.getPlayers().size();
	}

	public String playerName() {
		return gameSet.getPlayers().get(gameSet.getCurrentPlayer()).getName();
	}

	private boolean didPlayerWin() {
		// La partie se termine lorsqu'un joueur obtient 6 coins
		return !(gameSet.getPlayerCoins() == 6);
	}
}
