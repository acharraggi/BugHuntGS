package com.mikesilversides.bughuntgs.core;

import com.mikesilversides.bughuntgs.core.Score;
import java.util.Arrays;

/** This class implements an in memory store for the player scores.
 *  <p><i>Note: This class is not meant to be production ready.
 *  It should be replaced with a database backed store.</i></p>
 * 
 * @author Mike Silversides
 *
 */
public class Scores {
	private Score[] theScores; 
	private int numberOf;
	private boolean isSorted;

	/**
	 * basic constructor for Scores. Creates an array of 100 Scores.
	 */
    public Scores() {
    	this.theScores = new Score[100];
    	this.numberOf = 0;
    	this.isSorted = false;
    }
    
    /**
     * Basic Score getter by id.
     * @param  id  The id of the player score to find.
     * @return The player score for that id, null if not found.
     */
	public Score getScore(long id) {
		if (numberOf == 0) return null;
		
		for(int i=0; i<numberOf; i++){
           if (theScores[i].getId() == id){
            	return theScores[i];
            }
		}
		return null;  		
	}

	/**
	 * Basic Score save. 
	 * <p><i>Note: if there are too many scores, we just ignore this one.</i></p>
	 * @param score
	 */
	public void putScore(Score score) {
		if (numberOf < theScores.length) {
			theScores[numberOf++] = score;
			isSorted = false;
		}
		// else too many scores, just ignore.
	}
	
	/**
	 * Gets the top 10 scores from the saved scores.
	 * Does an array sort if needed and returns an array of the top 10.
	 * @return An array of player scores, no more than length 10.
	 */
	public Score[] top10() {
		int top10size = numberOf<10 ? numberOf : 10;
		Score[] top10scores = new Score[top10size];
		
		if (top10size > 0) {
			if (!isSorted){
				Arrays.sort(theScores,0,numberOf);
				isSorted = true;
			}
			for(int i=0; i<top10size; i++){
				top10scores[i] = theScores[i];
			}
		}
		return top10scores;
	}
}

