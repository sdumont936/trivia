package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class GameSet {
	private ArrayList<Player> players;
	QuestionsSet questions;
	int currentPlayer;

	public GameSet() {
		this.players = new ArrayList<Player>();
		this.questions = new QuestionsSet();
		this.currentPlayer = 0;
	}
	
	public QuestionsSet getQuestions() {
		return questions;
	}

	public boolean addPlayer(String playerName) {
		players.add(new Player(playerName));
		System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
		return true;
	}

	public String askQuestionToCurrentPlayer() {
		Player player = this.getCurrentPlayer();
		return questions.askQuestionToAPlayer(player);
	}

	public Player getCurrentPlayer() {
		return this.players.get(currentPlayer);
	}

	public void getNextPlayer() {
		currentPlayer++;
		if (currentPlayer == players.size())
			currentPlayer = 0;
	}

	public int getPlayerCoins() {
		return this.getCurrentPlayer().getPurse();
	}

	public void giveACoinToTheCurrentPlayer() {
		this.getCurrentPlayer().addACoinToPurse();
	}

	public void moveCurrentPlayerToPenaltyBox() {
		getCurrentPlayer().moveToPenaltyBox();
	}

	public boolean didCurrentPlayerWin() {
		// La partie se termine lorsqu'un joueur obtient 6 coins
		return (getPlayerCoins() == 6);
	}

	public int countPlayers() {
		return players.size();
	}

	public String getCurrentQuestionCategory() {
		return getQuestions().currentCategory(getCurrentPlayer());
	}

	public void moveCurrentPlayer(int diceRoll) {
		getCurrentPlayer().move(diceRoll);
	}

	public int getCurrentPlayerPlace() {
		return getCurrentPlayer().getPlace();
	}

	public void releaseCurrentPlayerFromPenaltyBox() {
		getCurrentPlayer().releasePlayerFromThePenaltyBox();
	}

	public boolean isCurrentPlayerInPenaltyBox() {
		return getCurrentPlayer().isInPenaltyBox();
	}

	public String currentPlayerName() {
		return getCurrentPlayer().getName();
	}
}