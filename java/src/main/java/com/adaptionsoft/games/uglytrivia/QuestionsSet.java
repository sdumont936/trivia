package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class QuestionsSet {

	LinkedList<String> popQuestions = new LinkedList();
	LinkedList<String> scienceQuestions = new LinkedList();
	LinkedList<String> sportsQuestions = new LinkedList();
	LinkedList<String> rockQuestions = new LinkedList();

	public QuestionsSet() {
		createQuestionsSet();
	}

	private void createQuestionsSet() {
		for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast("Rock Question " + i);
		}
	}

	public String currentCategory(Player player) {
		int place = player.getPlace();
		if (place == 0 || place == 4 || place == 8)
			return "Pop";
		if (place == 1 || place == 5 || place == 9)
			return "Science";
		if (place == 2 || place == 6 || place == 10)
			return "Sports";
		return "Rock";
	}

	public String askQuestionToAPlayer(Player player) {
		String currentCategory = currentCategory(player);
		String question = "";
		if (currentCategory == "Pop")
			question = this.popQuestions.removeFirst();
		if (currentCategory == "Science")
			question = this.scienceQuestions.removeFirst();
		if (currentCategory == "Sports")
			question = this.sportsQuestions.removeFirst();
		if (currentCategory == "Rock")
			question = this.rockQuestions.removeFirst();
		return question;
	}

}
