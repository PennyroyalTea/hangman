package ru.ioffe.school.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PageGenerator {
	
	private static String read(String file) throws IOException {
		StringBuilder stb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String s = br.readLine();
		while (s != null) {
			stb.append(s);
			s = br.readLine();
		}
		br.close();
		return stb.toString();
	}
	
	private static String makeButton(char c) {
		return "<input type=\"submit\" name=\"move\" value=\"" + c + "\" class=\"" + Settings.buttonHTMLClass + "\">";
	}

	private static String generateButtons(Model m, int id) {
		StringBuilder result = new StringBuilder();
		result.append("<form method=\"get\" action=\"hangman\" class=\"" + Settings.formHTMLClass + "\">");
		result.append("<input type=\"text\" value=\"" + id + "\" hidden name=\"id\">");
		ArrayList<Character> list = m.getUnusedLetters();
		for (char c : list) {
			result.append(makeButton(c));
		}
		result.append("</form>");
		return result.toString();
	}
	
	public static String newGame() throws IOException {
		String result = read(Settings.newGamePagePattern);
		Random r = new Random();
		int seed = r.nextInt(Settings.MAX_SEED) + 1;
		result = result.replace(Settings.randomSeedPattern, "" + seed);
		return result;
	}

	public static String continueGame(Model m, int id) throws IOException {
		String result = read(Settings.continueGamePagePattern);
		result = result.replace(Settings.wordPattern, m.getWordWithSpaces());
		result = result.replace(Settings.mistakesCounterPattern, "" + m.wrongTries());
		result = result.replace(Settings.buttonsListPattern, generateButtons(m, id));
		return result;
	}
	
	public static String endGame(Model m) throws IOException {
		String result = read(Settings.endGamePagePattern);
		result = result.replace(Settings.wordPattern, m.getWordWithSpaces());
		result = result.replace(Settings.mistakesCounterPattern, "" + m.wrongTries());
		result = result.replace(Settings.mistakesComment, generateComment(m.wrongTries()));
		return result;
	}
	
	private static String generateComment(int x) {
		if (x == 0) {
			return "You've guessed the word without any mistakes! Are you a cheater?";
		}
		String result = "You've mistaken " + (x < 8 ? "only " : "") + "<span class=\""+ Settings.mistakesHTMLClass + "\">" + x + "</span> time" + (x == 1 ? "" : "s") + ".";
		if (x < 3) {
			result += " It's a very good result!";
		} else if (x < 8) {
			result += " Not bad!";
		}
		return result;
	}
}
 