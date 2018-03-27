package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class QuestionsSet {

	LinkedList popQuestions = new LinkedList();
	LinkedList scienceQuestions = new LinkedList();
	LinkedList sportsQuestions = new LinkedList();
	LinkedList rockQuestions = new LinkedList();

	public QuestionsSet() {
		createQuestionsSet();
	}

	private void createQuestionsSet() {
		for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
	}
	
	public LinkedList getPopQuestions() {
		return popQuestions;
	}

	public LinkedList getScienceQuestions() {
		return scienceQuestions;
	}

	public LinkedList getSportsQuestions() {
		return sportsQuestions;
	}

	public LinkedList getRockQuestions() {
		return rockQuestions;
	}
	
	private String createRockQuestion(int index){
		return "Rock Question " + index;
	}


}
