package ru.ioffe.school.hangman;

import java.io.IOException;
import java.util.ArrayList;

import com.google.appengine.api.datastore.*;

/*
 * Let the language be English only 
 * 
 */

public class Model {

	final private int SIZE = 26;
	final private char space = Settings.spaceChar;
	
	final private String propertyWordName = Settings.propertyWordName;
	final private String propertyUnusedName = Settings.propertyUnusedName;
	
	
	private String word;
	private boolean[] used;
	
	Model() throws IOException {
		this.word = WordBase.getRandomWord();
		this.used = new boolean[SIZE];
	}
	
	Model(Entity entity) {
		this.word = (String) entity.getProperty(propertyWordName);
		String unusedListString = (String) entity.getProperty(propertyUnusedName);
		this.used = new boolean[SIZE];
		for (int i = 0; i < SIZE; ++i) {
			this.used[i] = true;
		}
		for (int i = 0; i < unusedListString.length(); ++i) {
			char c = unusedListString.charAt(i);
			this.used[c - 'a'] = false;
		}
	}
	
	int wrongTries() {
		int ans = 0;
		
		boolean[] isInWord = new boolean[this.SIZE];
		for (int i = 0; i < word.length(); ++i) {
			char c = word.charAt(i);
			isInWord[c - 'a'] = true;
		}
		for (int i = 0; i < this.SIZE; ++i) {
			if (this.used[i] && !isInWord[i]) {
				++ans;
			}
		}
		return ans;
	}
	
	boolean isOver() {
		String res = this.getWordWithSpaces();
		String word = this.word;
		return res.equals(word);
	}
	
	boolean makeMove(char c) {
		c = Character.toLowerCase(c);
		if (c < 'a' || c > 'z') {
			return false;
		}
		if (used[c - 'a']) {
			return false;
		}
		used[c - 'a'] = true;
		return true;
	}
	
	ArrayList<Character> getUnusedLetters() {
		ArrayList<Character> res = new ArrayList<>();
		for (int i = 0; i < SIZE; ++i) {
			if (!used[i]) {
				res.add((char)(i + 'a'));
			}
		}
		return res;	
	}
	
	String getWordWithSpaces() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < word.length(); ++i) {
			char curChar = word.charAt(i);
			if (used[curChar - 'a']) {
				builder.append(curChar);
			} else {
				builder.append(space);
			}
		}
		return builder.toString();
	}
	
	Entity toEntity(int id) {
		Entity cur = new Entity(Settings.entityName, id);
		cur.setProperty(propertyWordName, this.word);
		ArrayList<Character> unusedList = this.getUnusedLetters();
		String s = "";
		for (char c : unusedList) {
			s += c;
		}
		cur.setProperty(propertyUnusedName, s);
		return cur;
	}
}
