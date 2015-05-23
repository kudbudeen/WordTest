// Copyright (c) 2015 statement goes here
package com.mf.wordtest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/**
 * This class counts the number of characters for each word in the file and list
 * the longest and short words
 * 
 */
public class LongAndShortWordFinder {

	/**
	 * This methods counts the number of characters for each word in the file
	 * and returns the sorted list with longest word on top.
	 * 
	 * @param fileName
	 * @return list of entries with sorted by their count. The Entry has word as
	 *         the key and count as value
	 */
	public List<Entry<String, Integer>> findWordsAndSortByLength(String fileName) {

		// List to longest words on top
		List<Entry<String, Integer>> sortedList = new ArrayList<Entry<String, Integer>>();

		// Map that holds word as a key and their length as values
		Map<String, Integer> wordLengthMap = new LinkedHashMap<String, Integer>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			// Read the file line by line
			for (String line; (line = br.readLine()) != null;) {
				// Split the line by spaces to find the words
				StringTokenizer tokenizer = new StringTokenizer(line);
				while (tokenizer.hasMoreTokens()) {
					// Get the word and trim it to avoid extra spaces
					String word = tokenizer.nextToken().trim().toLowerCase();
					// Find the length of the words and add it to map
					if (!wordLengthMap.containsKey(word))
						wordLengthMap.put(word, word.length());
				}
			}

			// Sort the map based on the word's length. The longest word is on
			// the top
			sortedList.addAll(wordLengthMap.entrySet());
			Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
				public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
					return (o2.getValue()).compareTo(o1.getValue());
				}
			});

			// clean up
			wordLengthMap.clear();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception ex) {
			}
		}

		return sortedList;
	}

	/**
	 * Main method to execute.
	 * 
	 * Make sure the file name is passed as the first command line argument Or
	 * assign the fileName explicitly within this program
	 * 
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		String fileName = "";
		if (args.length > 0)
			fileName = args[0];

		// Else Assign file name explicitly here
		// fileName = "C:/mydir/myfile.txt"

		// check for the file name
		if (fileName == null || fileName.equals("")) {
			System.err.println("Provide the filename to find the longest and shortest words");
			return;
		}

		// Instantiate this class
		LongAndShortWordFinder wordCtFinder = new LongAndShortWordFinder();
		// Get the list of words and their count sorted
		List<Entry<String, Integer>> sortedList = wordCtFinder.findWordsAndSortByLength(fileName);
		// Print them
		System.out.println("The following words are printed in the order of longest to shortest");
		for (Map.Entry<String, Integer> entry : sortedList) {
			System.out.println(entry.getKey());
		}
	}

}
