package com.revature.eval.java.core;

import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

public class EvaluationService {

	/**
	 * 1. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		// TODO Write an implementation for this method declaration
		
		phrase = phrase.replace("-", " ");
		String [] splPhr = phrase.split(" ");
		
		char firLet;
		int phrIdx = 0;
		String nym = "";
		
		for (String word : splPhr) {
			
			firLet = splPhr[phrIdx].charAt(0);
			firLet = Character.toUpperCase(firLet);
			phrIdx++;
			nym = nym + firLet;
			
		}
		
		return nym; 
	}

	/**
	 * 2. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		// TODO Write an implementation for this method declaration
		string = string.toLowerCase();
		int wordScore = 0;
		
		String [] scoreString = string.split("");
		
		String worth1 = "a e i o u l n r s t";
		String worth2 = "d g";
		String worth3 = "b c m p";
		String worth4 = "f h v w y";
		String worth5 = "k";
		String worth8 = "j x";
		String worth10 = "q z";
		
		String [] pt1 = worth1.split(" ");
		String [] pt2 = worth2.split(" ");
		String [] pt3 = worth3.split(" ");
		String [] pt4 = worth4.split(" ");
		String [] pt5 = worth5.split(" ");
		String [] pt8 = worth8.split(" ");
		String [] pt10 = worth10.split(" ");
		
		for (String letter : scoreString) {
			
			if (letter.equals(pt1 [0]) || letter.equals(pt1 [1]) || letter.equals(pt1 [2]) || letter.equals(pt1 [3]) || letter.equals(pt1 [4])
					|| letter.equals(pt1 [5]) || letter.equals(pt1 [6]) || letter.equals(pt1 [7]) || letter.equals(pt1 [8]) || letter.equals(pt1 [9])) {
				wordScore += 1;
			}
			else if (letter.equals(pt2 [0]) || letter.equals(pt2 [1])) {
				wordScore += 2;
			}
			else if (letter.equals(pt3 [0]) || letter.equals(pt3 [1]) || letter.equals(pt3 [2]) || letter.equals(pt3 [3])) {
				wordScore += 3;
			}
			else if (letter.equals(pt4 [0]) || letter.equals(pt4 [1]) || letter.equals(pt4 [2]) || letter.equals(pt4 [3]) || letter.equals(pt4 [4])) {
				wordScore += 4;
			}
			else if (letter.equals(pt5 [0])) {
				wordScore += 5;
			}
			else if (letter.equals(pt8 [0]) || letter.equals(pt8 [1])) {
				wordScore += 8;
			}
			else {
				wordScore += 10;
			}
		}
		
		return wordScore;
	}

	/**
	 * 3. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		// TODO Write an implementation for this method declaration
		string = string.replace("(", "");
		string = string.replace(")", "");
		string = string.replace(" ", "");
		string = string.replace("+", "");
		string = string.replace("-", "");
		string = string.replace(".", "");
		
		String [] strTst = string.split("");
		
		if (strTst.length > 10) {
				throw new IllegalArgumentException("Too Many Digits");
		}
		
		else {
			
			int strIdx = 0;
			
		for (int i = 0; i < 11; i++) {
			
			if (strTst[strIdx].equals("0") || strTst[strIdx].equals("1") || strTst[strIdx].equals("2") || strTst[strIdx].equals("3") || strTst[strIdx].equals("4") ||
					strTst[strIdx].equals("5") || strTst[strIdx].equals("6") || strTst[strIdx].equals("7") || strTst[strIdx].equals("8") || strTst[strIdx].equals("9")) {
			
				strIdx++;
				
				if(i == 9) {
					return string;
				}
				
			}
			
			else {	
				i = 11;
				throw new IllegalArgumentException("Invalid Symbol");		
			}
			
			
		}
		
		return "fallthrough";
		
		}
		
	}

	/**
	 * 4. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		// TODO Write an implementation for this method declaration
		//System.out.println("Phase1: " + string);
		string = string.replace(",", " ");
		//System.out.println("Phase2: " + string);
		string = string.replace(" \n", " ");
		//System.out.println("Phase3: " + string);
		String [] wrdComp = string.split(" ");
		
		int compDex = 0;
		int inLoop = 0;
		int wrdValue = 1;
		Map<String, Integer> wordsAndValues = new HashMap<>();
		
		for (String word : wrdComp) {
			
			
			//System.out.println(inLoop + "-" + word + " is looping");
			
			//if (Current word matches something that has already been through the loop) {
			// Don't put it through the loop
			//}
			//else { Test it
			for (int i = wrdComp.length - 1; i > -1; i--) {
				
				if(inLoop == compDex) {
					//System.out.println("Cancel, " + inLoop + "-" + word + " is " + compDex + "-" + wrdComp[compDex]);
					compDex++;
				}
				
				else if (word.equals(wrdComp[compDex])){
					//System.out.println("Success! " + inLoop + "-" + word + " matches " + compDex + "-" + wrdComp[compDex]);
					wrdValue ++;
					compDex++;
				}
				
				else {
					//System.out.println("Pass, " + inLoop + "-" + word + " isn't " + compDex + "-" + wrdComp[compDex]);
					compDex++;
				}
						
			}
			// }
			
			//System.out.println("DONE! "+ inLoop + "-" + word + " value is " +  wrdValue);
			wordsAndValues.put(word, wrdValue); 
			inLoop ++;
			compDex = 0;
			wrdValue = 1;
			//System.out.println("New looper! " + inLoop);
			//Add it to the map with its' value
			//System.out.println(word + " appearances = " + wrdValue);
		}
		
		return wordsAndValues;
	}

	/**
	 * 5. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			// TODO Write an implementation for this method declaration
			//System.out.println("Start, " + sortedList + " is in");
			Object [] rawArray = sortedList.toArray();
			int [] useArray = new int [rawArray.length];
			int useIdx = 0;
			int mathHolder;
			boolean isEven = false;
			
			for (Object number : rawArray) {
				//System.out.println("Begin! " + number + " has entered!"); 
				useArray[useIdx] = Integer.valueOf(rawArray[useIdx].toString());
				//System.out.println("Loop! " + useIdx + "-" + useArray[useIdx] + " is the same as " + rawArray[useIdx]);
				useIdx++;
			}
			
			if ((useArray.length & 1) == 0) {
				//System.out.println(useArray.length + " is even!");
				useIdx = useArray.length / 2;
				System.out.println(useIdx);
				isEven = true;
				
				
			}
			
			else {
				//System.out.println(useArray.length + " is odd!");
				useIdx = useArray.length / 2 + 1;
				//System.out.println(useIdx + " odd");
			}
			
			if (Integer.valueOf(t.toString()) == useArray[useIdx]) {
				System.out.println("Quick Catch! - " + sortedList + "-" + t);
				return useIdx;
			}
			
			else if (Integer.valueOf(t.toString()) < useArray[useIdx]) {
				// First half split
				System.out.println("Entered - " + sortedList + "-" + t);
				if (isEven) {
				useIdx = useIdx / 2;
				mathHolder = useIdx;
				}
				
				else {
				useIdx = useIdx / 2 + 1;
				mathHolder = useIdx;
				}
				
				if (Integer.valueOf(t.toString()) < useArray[useIdx]) {
					// lower left half
					for (int i = useIdx; i > -1; i--) {
						
						if (Integer.valueOf(t.toString()) == useArray[useIdx]) {
							return useIdx;
						}
						
						else {
							useIdx--;
						}
						
					}
					
					useIdx = mathHolder;
					
				}
				
				else if (Integer.valueOf(t.toString()) > useArray[useIdx]) {
					// upper left half
					for (int i = useIdx; i < useIdx - 1 * 2; i++) {
						
						if (Integer.valueOf(t.toString()) == useArray[useIdx]) {
							return useIdx;
						}
						
						else {
							useIdx++;
						}
						
					}
					
					useIdx = mathHolder;
					
				}
				
			}
			
			else if (Integer.valueOf(t.toString()) > useArray[useIdx]) {
				// second half split
				System.out.println("Entered - " + sortedList + "-" + t);
				if (isEven) {
				useIdx = useIdx / 2 + useIdx;
				mathHolder = useIdx;
				}
				
				else {
				useIdx = useIdx / 2 + useIdx + 1;
				mathHolder = useIdx;
				}
				
				if (Integer.valueOf(t.toString()) < useArray[useIdx]) {
					// lower right half
					for (int i = useIdx; i > -1; i--) {
						
						if (Integer.valueOf(t.toString()) == useArray[useIdx]) {
							return useIdx;
						}
						
						else {
							useIdx--;
						}
						
					}
					
					useIdx = mathHolder;
					
				}
				
				else if (Integer.valueOf(t.toString()) > useArray[useIdx]) {
					// upper left half
					for (int i = useIdx; i < useIdx - 1 * 2; i++) {
						
						if (Integer.valueOf(t.toString()) == useArray[useIdx]) {
							return useIdx;
						}
						
						else {
							useIdx++;
						}
						
					}
					
					useIdx = mathHolder;
					
				}
				
				else {
					System.out.println("SearchSlip - " + sortedList + "-" + t);
					return 0;
				}
				
			}
			System.out.println("Fallthrough - " + sortedList + "-" + t);
			return 0;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 6. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		// TODO Write an implementation for this method declaration
		return false;
	}

	/**
	 * 7. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		// TODO Write an implementation for this method declaration
		return null;
	}


	/**
	 * 8-9. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 8
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			// TODO Write an implementation for this method declaration
			return null;
		}

		/**
		 * Question 9
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			// TODO Write an implementation for this method declaration
			return null;
		}
	}

	/**
	 * 10. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

}
