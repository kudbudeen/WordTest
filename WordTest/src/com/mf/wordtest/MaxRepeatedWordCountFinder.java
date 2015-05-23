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
 * This class can be used to find the words and their count in a file. Also
 * sorts them maximum repeated work count
 * 
 */
public class MaxRepeatedWordCountFinder {

	/**
	 * This methods finds the count of the words from the given file and returns
	 * the sorted list with maximum repeated word count on top.
	 * 
	 * @param fileName
	 * @return list of entries with sorted by their count. The Entry has word as
	 *         the key and count as value
	 */
	public List<Entry<String, Integer>> findWordsAndSortByCount(String fileName) {

		// List to hold sorted values
		// Entry has the word as key and count as the value
		List<Entry<String, Integer>> sortedList = new ArrayList<Entry<String, Integer>>();

		// Map that holds word as a key and count as the values
		Map<String, Integer> countMap = new LinkedHashMap<String, Integer>();
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
					// Increase the count by one for each occurrence
					int wordCt = 1;
					if (countMap.containsKey(word))
						wordCt = wordCt + countMap.get(word);
					// Add it to the count map
					countMap.put(word, wordCt);
				}
			}

			// Sort the map based on their count. The maximum count should be at
			// the top.
			sortedList.addAll(countMap.entrySet());
			Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
				public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
					return (o2.getValue()).compareTo(o1.getValue());
				}
			});

			// clean up
			countMap.clear();

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
			System.err.println("Provide the filename to find the max word count");
			return;
		}

		// Instantiate this class
		MaxRepeatedWordCountFinder wordCtFinder = new MaxRepeatedWordCountFinder();
		// Get the list of words and their count sorted
		List<Entry<String, Integer>> sortedList = wordCtFinder.findWordsAndSortByCount(fileName);
		// Print them
		for (Map.Entry<String, Integer> entry : sortedList) {
			System.out.println(entry.getKey() + " repeated " + entry.getValue() + " times in the file");
		}
	}

}
