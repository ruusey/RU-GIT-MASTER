package com.writers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import com.models.Wrestler;
import com.owlike.genson.Genson;

public abstract class Index {

	HashMap<String, HashMap<Integer, Integer>> tokenMap = new HashMap<String, HashMap<Integer, Integer>>(
			100000);
	HashMap<Integer, Object> objectMap = new HashMap<Integer, Object>(10000);

	int findMatchLimit = 100;

	static String REGEX = "\t\n\r\f,. :<@!>()$+'-";

	protected void index(int id, String expression) {

		int tokenCount = 0;

		StringTokenizer st = new StringTokenizer(expression.toLowerCase(),
				REGEX, false);
		Vector<String> tokens = new Vector<String>();
		tokenCount = 0;
		while (st.hasMoreTokens()) {
			tokenCount++;
			String oToken = st.nextToken().toLowerCase();
			tokens.add(oToken);
		}

		String token = "";
		for (int l = 0; l < tokens.size(); l++) {
			token = (String) tokens.get(l);
			// DO PHRASES PARTIAL
			String subString = "";
			for (int l2 = 1; l2 <= token.length(); l2++) {
				try {
					subString = new String(token.substring(0, l2));
				} catch (Exception e) {
					e.printStackTrace();
				}
				// Vector of matching Topics with this Substring
				HashMap<Integer, Integer> matches = tokenMap.get(subString);
				if (matches == null) {
					matches = new HashMap<Integer, Integer>(100);
				}
				matches.put(id, id);
				tokenMap.put(subString, matches);
			}
			if (l == tokenCount - 1)
				break;
		}

	}

	public Vector<Object> search(String expression) {
		Vector<Object> results = new Vector<Object>();
		HashMap<Integer, Integer> currentMatches = null;
		HashMap<Integer, Integer> newMatches = null;
		HashMap<Integer, Integer> prevMatches = null;
		StringTokenizer st = new StringTokenizer(expression.toLowerCase(),
				REGEX, false);
		try {

			while (st.hasMoreTokens()) {
				String token = st.nextToken().toLowerCase();
				currentMatches = tokenMap.get(token);

				if (prevMatches == null) {
					prevMatches = currentMatches;
				}
				if (currentMatches == null) {
					return results;
				}

				newMatches = new HashMap<Integer, Integer>(
						currentMatches.size());

				for (Integer id : currentMatches.keySet()) {
					Integer testPrev = prevMatches.get(id);
					if (testPrev != null)
						newMatches.put(id, id);
				}

				prevMatches = newMatches;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		int matchCount = 0;
		for (Integer id : prevMatches.values()) {
			Object category = objectMap.get(id);
			if (category != null) {
				results.add(objectMap.get(id));
				matchCount++;
				if (matchCount > findMatchLimit) {
					break;
				}
			}
		}
		return results;
	}

	abstract public void buildWrestler(List<Wrestler> objectList);

	
}
