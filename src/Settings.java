package ru.ioffe.school.hangman;

public class Settings {
	public static final String entityName = "Model";
	public static final String propertyWordName = "word";
	public static final String propertyUnusedName = "unused";

	public static final int MAX_SEED = 10000;

	public static final String URLPropertyId = "id";
	public static final String URLPropertyMove = "move";

	public static final String newGamePagePattern = "hangman-newGame-servlet.html";
	public static final String endGamePagePattern = "hangman-end-servlet.html";
	public static final String continueGamePagePattern = "hangman-continue-servlet.html";

	public static final char spaceChar = '*';

	public static final String wordPattern = "%word";
	public static final String randomSeedPattern = "%seed";
	public static final String mistakesCounterPattern = "%mistakes";
	public static final String mistakesComment = "%comment";
	public static final String buttonsListPattern = "%unusedButtons";

	public static final String formHTMLClass = "letters";
	public static final String buttonHTMLClass = "letter";
	public static final String mistakesHTMLClass = "mistakes";
	
	public static final String wordDatabaseURL = "http://www.setgetgo.com/randomword/get.php";
}
