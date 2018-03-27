package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class GameSet {
	private ArrayList<Player> players;
	private int[] places;
	private int[] purses;
	private boolean[] penaltyBox;
	private QuestionsSet questions;

	public GameSet(ArrayList<Player> players, int[] places, int[] purses, boolean[] penaltyBox) {
		this.players = players;
		this.places = places;
		this.purses = purses;
		this.penaltyBox = penaltyBox;
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
}