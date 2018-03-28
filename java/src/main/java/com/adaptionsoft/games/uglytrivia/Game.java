package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Game {

	boolean isGettingOutOfPenaltyBox;
	GameSet gameSet = new GameSet();
	
	
	public void roll(int diceRoll) {
		System.out.println(gameSet.currentPlayerName() + " is the current player");
		System.out.println("They have rolled a " + diceRoll);

		if (gameSet.isCurrentPlayerInPenaltyBox()) {
			if (diceRoll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				gameSet.releaseCurrentPlayerFromPenaltyBox();
				System.out.println(gameSet.currentPlayerName() + " is getting out of the penalty box");
			} else {
				isGettingOutOfPenaltyBox = false;
				System.out.println(gameSet.currentPlayerName() + " is not getting out of the penalty box");
			}
		}

		if (!gameSet.isCurrentPlayerInPenaltyBox()) {
			moveAndAskQuestion(diceRoll);
		}

	}



	public void moveAndAskQuestion(int diceRoll) {
		gameSet.moveCurrentPlayer(diceRoll);
		String question = gameSet.askQuestionToCurrentPlayer();

		System.out.println(gameSet.currentPlayerName() + "'s new location is " + gameSet.getCurrentPlayerPlace());
		System.out.println("The category is " + gameSet.getCurrentQuestionCategory());
		System.out.println(question);
	}


	public boolean answerCorrectly() {
		if (gameSet.isCurrentPlayerInPenaltyBox() && isGettingOutOfPenaltyBox) {
			gameSet.releaseCurrentPlayerFromPenaltyBox();
		}
		
		if (!gameSet.isCurrentPlayerInPenaltyBox()) {
			System.out.println("Answer is correct !!!!");
			gameSet.giveACoinToTheCurrentPlayer();
			System.out.println(gameSet.currentPlayerName() + " now has " + gameSet.getPlayerCoins() + " Gold Coins.");
		}
		
		boolean winner = false;
		if(gameSet.didCurrentPlayerWin()) {
			System.out.println(gameSet.currentPlayerName() + " WIN !!!");
			winner = true;
		}
		
		gameSet.getNextPlayer();
		return winner;
	}


	public void answerWrongly() {
		System.out.println("Question was incorrectly answered");
		System.out.println(gameSet.currentPlayerName() + " was sent to the penalty box");
		gameSet.moveCurrentPlayerToPenaltyBox();
		gameSet.getNextPlayer();
	}


	public boolean isPlayable() {
		return (countPlayers() >= 2);
	}

	public boolean add(String playerName) {
		return gameSet.addPlayer(playerName);
	}

	public int countPlayers() {
		return gameSet.countPlayers();
	}
}
