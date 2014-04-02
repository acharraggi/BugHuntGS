package com.mikesilversides.bughuntgs.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScoresTest {

	@Test
	public void test() {
		Scores testScores = new Scores();
		Score[] top10test = new Score[10];
		
		Score testScore = new Score(1,"testName",100);
		assertNull("getScore[1] should return null, no scores yet", testScores.getScore(1));
		
		testScores.putScore(testScore);
		assertSame("failed to get back a score that was added to Scores", testScore, testScores.getScore(1));
		assertEquals("id not the same",1,testScores.getScore(1).getId());
		assertEquals("playerName not the same","testName",testScores.getScore(1).getPlayerName());
		assertEquals("score not the same",100,testScores.getScore(1).getScore());
		
		top10test = testScores.top10();
		assertEquals("top10 list should have 1 entries",1,top10test.length);
		assertSame("new score isn't first in top10", testScore, top10test[0]);
		
		assertNull("getScore[5] should return null, 5 not added yet", testScores.getScore(5));
		
		for(int i=2; i<15; i++) {
			testScore = new Score(i,"testName",100+(i*2));
			testScores.putScore(testScore);
			assertSame("failed to get back a score that was added to Scores during mass add", testScore, testScores.getScore(i));
		}
		top10test = testScores.top10();
//		for(int i=0; i<top10test.length; i++) {
//			System.out.print(top10test[i].getPlayerName());
//			System.out.println(top10test[i].getScore());
//		}
		assertSame("last mass add score isn't first in top10", testScore, top10test[0]);
		assertEquals("top10 list should have 10 entries",10,top10test.length);
		
	}

}
