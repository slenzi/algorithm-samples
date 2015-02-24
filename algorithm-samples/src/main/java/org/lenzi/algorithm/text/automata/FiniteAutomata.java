package org.lenzi.algorithm.text.automata;

import java.util.ArrayList;
import java.util.List;

import com.google.common.primitives.Ints;

public class FiniteAutomata {

	public final static int ASCII = 128;
	public final static int ASCII_EXTENDED = 256;	
	
	// length of pattern
	private int patLen = 0;
	
	// length of input text
	private int txtLen = 0;
	
	// state transition table
	private int[][] stateTable = null;
	
	private String pattern = null;
	private String text = null;
	
	private int ALPHA_SIZE = ASCII_EXTENDED;
	
	public FiniteAutomata() {
		
	}
	
	/**
	 * Search for all occurrences of 'pattern' in 'text'. 
	 * 
	 * @param pattern
	 * @param text
	 */
	public int[] search(int alphaSize, String pattern, String text){
		
		initialize(alphaSize, pattern, text);
		
		List<Integer> indexes = new ArrayList<Integer>();
		
		int i = 0, j = 0;
		for (i = 0; i < txtLen; i++){
			j = stateTable[j][(int)text.charAt(i)];
			if(j == patLen){
				indexes.add(i - patLen + 1);
			}
		}
		return Ints.toArray(indexes);
		
	}
	
	/**
	 * Initialize state machine
	 */
	private void initialize(int alphaSize, String pattern, String text){
		
		this.ALPHA_SIZE = alphaSize;
		
		this.pattern = pattern;
		this.text = text;
		
		patLen = pattern.length();
		txtLen = text.length();
		
		stateTable = new int[patLen + 1][ALPHA_SIZE];
		
		computeStateTable();
		
		printStateTable();
		
	}
	
	/**
	 * Build state table which represents finite automata for patterns
	 */
	private void computeStateTable(){
		
		int i = 0, lps = 0, x = 0;
		
		// Fill entries in first row
		for (x = 0; x < ALPHA_SIZE; x++){
			stateTable[0][x] = 0;
		}
		stateTable[0][(int)pattern.charAt(0)] = 1;
		
		// Fill entries in other rows
		for (i = 1; i <= patLen; i++){
			
			// Copy values from row at index lps
			for (x = 0; x < ALPHA_SIZE; x++){
				stateTable[i][x] = stateTable[lps][x];
			}
			
			if (i == patLen) {
				break;
			}
			
			// Update the entry corresponding to this character
			stateTable[i][(int)pattern.charAt(i)] = i + 1;
			
			// Update lps for next row to be filled
			if(i < patLen){
				lps = stateTable[lps][(int)pattern.charAt(i)];
			}
			
		}
		
	}
	
	private void printStateTable(){
		int i = 0, j = 0;
		for (i = 0; i < stateTable.length; i++){
			for(j = 0; j < stateTable[i].length; j++){
				System.out.print(String.format("%1$-3d", stateTable[i][j]));
			}
			System.out.println();
		}
		System.out.println("\n");
	}
	
}
