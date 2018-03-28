package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class GameSet {
	private ArrayList<Player> players;
	private int[] places;
	private int[] purses;
	private boolean[] penaltyBox;
	private QuestionsSet questions;
	int currentPlayer;
	
	public GameSet() {
		this.players = new ArrayList<Player>();
		this.places = new int[6];
		this.purses = new int[6];
		this.penaltyBox = new boolean[6];
		this.questions = new QuestionsSet();
		this.currentPlayer = 0;
	}


	

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public int[] getPlaces() {
		return places;
	}

	public void setPlaces(int[] places) {
		this.places = places;
	}

	public int[] getPurses() {
		return purses;
	}

	public void setPurses(int[] purses) {
		this.purses = purses;
	}

	public boolean[] getPenaltyBox() {
		return penaltyBox;
	}

	public void setPenaltyBox(boolean[] penaltyBox) {
		this.penaltyBox = penaltyBox;
	}

	public QuestionsSet getQuestions() {
		return questions;
	}

	public void setQuestions(QuestionsSet questions) {
		this.questions = questions;
	}


	public boolean addPlayer(Game game, String playerName) {
		getPlayers().add(new Player(playerName));
	    getPlaces()[game.countPlayers()] = 0;
	    getPurses()[game.countPlayers()] = 0;
	    getPenaltyBox()[game.countPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + getPlayers().size());
		return true;
	}


	public void movePlayer(int diceRoll) {
		getPlaces()[currentPlayer] = getPlaces()[currentPlayer] + diceRoll;
		if (getPlaces()[currentPlayer] > 11) {
			getPlaces()[currentPlayer] = getPlaces()[currentPlayer] - 12;
		}
	}


	public void releasePlayerFromThePenaltyBox() {
		getPenaltyBox()[currentPlayer] = false;
	}


	public boolean isInPenaltyBox() {
		return getPenaltyBox()[currentPlayer];
	}


	public void askQuestion(Game game) {
		if (game.gameSet.currentCategory() == "Pop")
			System.out.println(getQuestions().getPopQuestions().removeFirst());
		if (game.gameSet.currentCategory() == "Science")
			System.out.println(getQuestions().getScienceQuestions().removeFirst());
		if (game.gameSet.currentCategory() == "Sports")
			System.out.println(getQuestions().getSportsQuestions().removeFirst());
		if (game.gameSet.currentCategory() == "Rock")
			System.out.println(getQuestions().getRockQuestions().removeFirst());
	}




	public String currentCategory() {
		if (getPlaces()[currentPlayer] == 0)
			return "Pop";
		if (getPlaces()[currentPlayer] == 1)
			return "Science";
		if (getPlaces()[currentPlayer] == 2)
			return "Sports";
		if (getPlaces()[currentPlayer] == 4)
			return "Pop";
		if (getPlaces()[currentPlayer] == 5)
			return "Science";
		if (getPlaces()[currentPlayer] == 6)
			return "Sports";
		if (getPlaces()[currentPlayer] == 8)
			return "Pop";
		if (getPlaces()[currentPlayer] == 9)
			return "Science";
		if (getPlaces()[currentPlayer] == 10)
			return "Sports";
		return "Rock";
	}




	public int getCurrentPlayer() {
		return this.currentPlayer;
	}

	public void getNextPlayer() {
		currentPlayer++;
		if (currentPlayer == getPlayers().size())
			currentPlayer = 0;
	}




	public void moveToPenaltyBox() {
		getPenaltyBox()[currentPlayer] = true;
	}




	public int getPlayerCoins() {
		return getPurses()[currentPlayer];
	}




	public void giveACoinToThePlayer() {
		getPurses()[currentPlayer]++;
	}
}