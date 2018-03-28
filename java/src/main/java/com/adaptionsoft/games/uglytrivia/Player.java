package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Player {

	private String name;
	private int place;
	private boolean inPenaltyBox;
	int purse;

	public Player(String name) {
		this.name = name;
		this.place = 0;
		this.inPenaltyBox = false;
		this.purse = 0;
	}

	public String getName() {
		return this.name;
	}

	public int getPlace() {
		return this.place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public void move(int diceRoll) {
		place = place + diceRoll;
		if (place > 11) {
			place = place - 12;
		}
	}

	public boolean isInPenaltyBox() {
		return this.inPenaltyBox;
	}

	public void moveToPenaltyBox() {
		this.inPenaltyBox = true;
	}

	public void releasePlayerFromThePenaltyBox() {
		this.inPenaltyBox = false;
	}

	public int getPurse() {
		return this.purse;
	}

	public void addACoinToPurse() {
		this.purse += 1;
	}

}
