package com.adaptionsoft.games.trivia;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.adaptionsoft.games.uglytrivia.Game;

public class GameTest {

	private static Game game;
	private static PrintStream printStream;
	private static ByteArrayOutputStream outputStream;
	
	@BeforeClass
	public static void initTests() {
		outputStream = new ByteArrayOutputStream(100);
		printStream = new PrintStream(outputStream);
		System.setOut(printStream);
		game = new Game();
		game.add("Player1");
		game.add("Player2");
	}
	
	
	@Test
	public void gameIsPlayableWhenAtLeast2PlayersJoinIn() {
		Assertions.assertThat(game.isPlayable()).isTrue();
	}
	
	@Test
	public void countPlayersReturn2WhenGameHave2Players() throws Exception {
		Assertions.assertThat(game.countPlayers()).isEqualTo(2);
	}

	@Ignore
	public void questionCreationIsOk() throws Exception {
//		Assertions.assertThat(game.createRockQuestion(2)).contains("Rock Question 2");
	}
	
	@Test
	public void checkMultipleRounds() throws Exception {
		resetSystemOutputStream();
		game.roll(2);
		LinkedList<String> stack = getSystemOutput();
		Assertions.assertThat(getNextOutput(stack)).contains("Player1 is the current player");
		Assertions.assertThat(getNextOutput(stack)).contains("They have rolled a 2");
		Assertions.assertThat(getNextOutput(stack)).contains("Player1's new location is 2");
		Assertions.assertThat(getNextOutput(stack)).contains("The category is Sports");
		Assertions.assertThat(getNextOutput(stack)).contains("Sports Question 0");
		
		// RG : Une réponse fausse envoie le joueur sur la touche
		resetSystemOutputStream();
		game.answerWrongly();
		stack = getSystemOutput();
		Assertions.assertThat(getNextOutput(stack)).contains("Question was incorrectly answered");
		Assertions.assertThat(getNextOutput(stack)).contains("Player1 was sent to the penalty box");
		
		
		// RG : Les méthodes wrongAnswer et wasCorrectlyAnswered donne la main au joueur suivant
		resetSystemOutputStream();
		game.roll(6);
		stack = getSystemOutput();
		Assertions.assertThat(getNextOutput(stack)).contains("Player2 is the current player");
		Assertions.assertThat(getNextOutput(stack)).contains("They have rolled a 6");
		Assertions.assertThat(getNextOutput(stack)).contains("Player2's new location is 6");
		Assertions.assertThat(getNextOutput(stack)).contains("The category is Sports");
		Assertions.assertThat(getNextOutput(stack)).contains("Sports Question 1");

		resetSystemOutputStream();
		game.answerWrongly();
		stack = getSystemOutput();
		Assertions.assertThat(getNextOutput(stack)).contains("Question was incorrectly answered");
		Assertions.assertThat(getNextOutput(stack)).contains("Player2 was sent to the penalty box");
		
		
		resetSystemOutputStream();
		game.roll(4);
		stack = getSystemOutput();
		Assertions.assertThat(getNextOutput(stack)).contains("Player1 is the current player");
		Assertions.assertThat(getNextOutput(stack)).contains("They have rolled a 4");
		Assertions.assertThat(getNextOutput(stack)).contains("Player1 is not getting out of the penalty box");
		
		// RG: Un second lancer de dés ne fait pas passer au jour suivant et ne le fait pas sortir de prison
		resetSystemOutputStream();
		game.roll(4);
		stack = getSystemOutput();
		Assertions.assertThat(getNextOutput(stack)).doesNotContain("Player2 is the current player");
		Assertions.assertThat(getNextOutput(stack)).contains("They have rolled a 4");
		Assertions.assertThat(getNextOutput(stack)).contains("Player1 is not getting out of the penalty box");
		
		// RG: Un troisième lancer de dés ne fait pas passer au jour suivant et ne le fait pas sortir de prison
		resetSystemOutputStream();
		game.roll(4);
		stack = getSystemOutput();
		Assertions.assertThat(getNextOutput(stack)).doesNotContain("Player2 is the current player");
		Assertions.assertThat(getNextOutput(stack)).contains("They have rolled a 4");
		Assertions.assertThat(getNextOutput(stack)).contains("Player1 is not getting out of the penalty box");
		
		// RG: Il est nécessaire de réaliser un wrong ou correctAnswer pour passer au joueur suivant
		resetSystemOutputStream();
		game.answerWrongly();
		stack = getSystemOutput();
		Assertions.assertThat(getNextOutput(stack)).contains("");

		resetSystemOutputStream();
		game.roll(5);
		stack = getSystemOutput();
		Assertions.assertThat(getNextOutput(stack)).contains("Player2 is the current player");
		Assertions.assertThat(getNextOutput(stack)).contains("They have rolled a 5");
		Assertions.assertThat(getNextOutput(stack)).contains("Player2 is getting out of the penalty box");
		Assertions.assertThat(getNextOutput(stack)).contains("Player2's new location is 11");
		Assertions.assertThat(getNextOutput(stack)).contains("The category is Rock");
		Assertions.assertThat(getNextOutput(stack)).contains("Rock Question 0");
		
		resetSystemOutputStream();
		game.answerCorrectly();
		stack = getSystemOutput();
		Assertions.assertThat(getNextOutput(stack)).contains("Answer is correct !!!!");
		Assertions.assertThat(getNextOutput(stack)).contains("Player2 now has 1 Gold Coins.");

		resetSystemOutputStream();
		game.roll(1);
		stack = getSystemOutput();
		Assertions.assertThat(getNextOutput(stack)).contains("Player1 is the current player");
		Assertions.assertThat(getNextOutput(stack)).contains("They have rolled a 1");
		Assertions.assertThat(getNextOutput(stack)).contains("Player1 is getting out of the penalty box");
		Assertions.assertThat(getNextOutput(stack)).contains("Player1's new location is 3");
		Assertions.assertThat(getNextOutput(stack)).contains("The category is Rock");
		// RG: Incrément du n° de question de même thématique
		// RG: Même thème de question que pour player 2
		Assertions.assertThat(getNextOutput(stack)).contains("Rock Question 1");
			
		resetSystemOutputStream();
		game.answerCorrectly();
		stack = getSystemOutput();
		Assertions.assertThat(getNextOutput(stack)).contains("Answer is correct !!!!");
		Assertions.assertThat(getNextOutput(stack)).contains("Player1 now has 1 Gold Coins.");
		
		resetSystemOutputStream();
		game.roll(1);
		game.answerCorrectly();
		stack = getSystemOutput();
		Assertions.assertThat(getNextOutput(stack)).contains("Player2 is the current player");
		Assertions.assertThat(getNextOutput(stack)).contains("They have rolled a 1");
		Assertions.assertThat(getNextOutput(stack)).contains("Player2's new location is 0");
		Assertions.assertThat(getNextOutput(stack)).contains("The category is Pop");
		Assertions.assertThat(getNextOutput(stack)).contains("Pop Question 0");
		Assertions.assertThat(getNextOutput(stack)).contains("Answer is correct !!!!");
		// RG : 1 coin / réponse correcte
		Assertions.assertThat(getNextOutput(stack)).contains("Player2 now has 2 Gold Coins.");
		
		resetSystemOutputStream();
		game.roll(1);
		game.answerCorrectly();
		stack = getSystemOutput();
		resetSystemOutputStream();
		game.roll(1);
		game.answerCorrectly();
		stack = getSystemOutput();
		Assertions.assertThat(getNextOutput(stack)).contains("Player2 is the current player");
		Assertions.assertThat(getNextOutput(stack)).contains("They have rolled a 1");
		Assertions.assertThat(getNextOutput(stack)).contains("Player2's new location is 1");
		Assertions.assertThat(getNextOutput(stack)).contains("The category is Science");
		Assertions.assertThat(getNextOutput(stack)).contains("Science Question 0");
		Assertions.assertThat(getNextOutput(stack)).contains("Answer is correct !!!!");
		// RG : 1 coin / réponse correcte
		Assertions.assertThat(getNextOutput(stack)).contains("Player2 now has 3 Gold Coins.");
		
	}





	private static void resetSystemOutputStream() {
		outputStream = new ByteArrayOutputStream();
		printStream = new PrintStream(outputStream);
		System.setOut(printStream);
	}
	
	private LinkedList<String> getSystemOutput() {
		LinkedList<String> stackOfStrings = new LinkedList<>();
		printStream.flush();
		for(String s : outputStream.toString().split("\n")) {
			stackOfStrings.add(s);
		}
		return stackOfStrings;
	}
		
	private String getNextOutput(LinkedList<String> stack) {
		return stack.pollFirst();
	}


}
