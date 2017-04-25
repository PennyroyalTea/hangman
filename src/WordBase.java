package ru.ioffe.school.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WordBase {
	
	private static String getText(String URLText) throws IOException {
		
		System.out.println("Loading the word from " + URLText);
		
		// StringBuilder to store our HTML code. Now it's empty.
		StringBuilder resultBuilder = new StringBuilder();
		
		// URL object which helps your PC understand URL properly
		URL url = new URL(URLText);
		
		//Connection with this url
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("GET");
		
		//reader which reads from this connection
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		//reading all the text from url to resultBuilder
		String s = reader.readLine();
		while (s != null) {
			resultBuilder.append(s + "\n");
			s = reader.readLine();
		}
		
		//closing the BufferedReader object
		reader.close();
		
		System.out.println("Result is : " + resultBuilder.toString());
		
		//returning String representation of the text
		return resultBuilder.toString();
	}
	
	static String getRandomWord() throws IOException {
		String word;
		do {
			word = getText(Settings.wordDatabaseURL);			
		} while (Character.isUpperCase(word.charAt(0)));
		return word.toLowerCase().trim();
	}
}
