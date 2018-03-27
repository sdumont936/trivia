package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Game {
    GameSet data = new GameSet(new ArrayList<Player>(), new int[6], new int[6], new boolean[6]);
	int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public Game(){
    	data.setQuestions(new QuestionsSet());
    }


	
	public boolean isPlayable() {
		return (countPlayers() >= 2);
	}

	public boolean add(String playerName) {
	    data.getPlayers().add(new Player(playerName));
	    data.getPlaces()[countPlayers()] = 0;
	    data.getPurses()[countPlayers()] = 0;
	    data.getPenaltyBox()[countPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + data.getPlayers().size());
		return true;
	}
	
	public int countPlayers() {
		return data.getPlayers().size();
	}

	public void roll(int roll) {
		System.out.println(playerName() + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (data.getPenaltyBox()[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(playerName() + " is getting out of the penalty box");
				data.getPlaces()[currentPlayer] = data.getPlaces()[currentPlayer] + roll;
				if (data.getPlaces()[currentPlayer] > 11) data.getPlaces()[currentPlayer] = data.getPlaces()[currentPlayer] - 12;
				
				System.out.println(playerName() 
						+ "'s new location is " 
						+ data.getPlaces()[currentPlayer]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(playerName() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			data.getPlaces()[currentPlayer] = data.getPlaces()[currentPlayer] + roll;
			if (data.getPlaces()[currentPlayer] > 11) data.getPlaces()[currentPlayer] = data.getPlaces()[currentPlayer] - 12;
			
			System.out.println(playerName() 
					+ "'s new location is " 
					+ data.getPlaces()[currentPlayer]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			System.out.println(data.getQuestions().getPopQuestions().removeFirst());
		if (currentCategory() == "Science")
			System.out.println(data.getQuestions().getScienceQuestions().removeFirst());
		if (currentCategory() == "Sports")
			System.out.println(data.getQuestions().getSportsQuestions().removeFirst());
		if (currentCategory() == "Rock")
			System.out.println(data.getQuestions().getRockQuestions().removeFirst());		
	}
	
	
	private String currentCategory() {
		if (data.getPlaces()[currentPlayer] == 0) return "Pop";
		if (data.getPlaces()[currentPlayer] == 1) return "Science";
		if (data.getPlaces()[currentPlayer] == 2) return "Sports";
		if (data.getPlaces()[currentPlayer] == 4) return "Pop";
		if (data.getPlaces()[currentPlayer] == 5) return "Science";
		if (data.getPlaces()[currentPlayer] == 6) return "Sports";
		if (data.getPlaces()[currentPlayer] == 8) return "Pop";
		if (data.getPlaces()[currentPlayer] == 9) return "Science";
		if (data.getPlaces()[currentPlayer] == 10) return "Sports";
		return "Rock";
	}

	public boolean answerCorrectly() {
		if (data.getPenaltyBox()[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				data.getPurses()[currentPlayer]++;
				System.out.println(playerName()
						+ " now has "
						+ data.getPurses()[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == data.getPlayers().size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == data.getPlayers().size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer is correct !!!!");
			data.getPurses()[currentPlayer]++;
			System.out.println(playerName()
					+ " now has "
					+ data.getPurses()[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == data.getPlayers().size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean answerWrongly(){
		System.out.println("Question was incorrectly answered");
		System.out.println(playerName() + " was sent to the penalty box");
		data.getPenaltyBox()[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == data.getPlayers().size()) currentPlayer = 0;
		return true;
	}

	private String playerName() {
		return data.getPlayers().get(currentPlayer).getName();
	}


	private boolean didPlayerWin() {
		return !(data.getPurses()[currentPlayer] == 6);
	}
}
