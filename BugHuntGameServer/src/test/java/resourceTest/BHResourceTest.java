package resourceTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mikesilversides.bughuntgs.resources.BugHuntResource;
import com.mikesilversides.bughuntgs.core.*;

public class BHResourceTest {

//	@Before
//	public void setUp() throws Exception {
//	}
	Scores theScores = new Scores();
	
	BugHuntResource resTest = new BugHuntResource(theScores);

	@Test
	public void test() {

		Score[] testScores = new Score[10];

		
		Score score1 = resTest.saveScore(new InScore("testName1",100));
		assertSame("unable to get score back for test 1",score1,resTest.getScore(score1.getId()));
		
		testScores = resTest.getScores();
		assertEquals("number of top10 scores is not equal to 1",1,testScores.length);
		assertSame("score1 is not first",score1,testScores[0]);
		
		Score score2 = resTest.saveScore(new InScore("testName2",150));
		assertSame("unable to get score back for test 2",score2,resTest.getScore(score2.getId()));
		
		testScores = resTest.getScores();
		assertEquals("number of top10 scores is not equal to 2",2,testScores.length);
		assertSame("score2 is not first",score2,testScores[0]);
		assertSame("score1 is not second",score2,testScores[0]);
		
		Score score3 = resTest.saveScore(new InScore("testName3",125));
		assertSame("unable to get score back for test 3",score3,resTest.getScore(score3.getId()));
		
		testScores = resTest.getScores();
		assertEquals("number of top10 scores is not equal to 3",3,testScores.length);
		assertSame("score2 is not first",score2,testScores[0]);
		assertSame("score3 is not second",score3,testScores[1]);
		assertSame("score1 is not third",score1,testScores[2]);
		
		for(int i=0; i<10; i++) {
			Score scoreI = resTest.saveScore(new InScore("testNameI",i));
			assertSame("unable to get score back for test i",scoreI,resTest.getScore(scoreI.getId()));
		}
		
		testScores = resTest.getScores();
		assertEquals("number of top10 scores is not equal to 10",10,testScores.length);
//		for (int i=0; i<testScores.length; i++) {
//			System.out.print(testScores[i].getPlayerName()+": ");
//			System.out.println(testScores[i].getScore());
//		}
		assertSame("score2 is not first",score2,testScores[0]);
		assertSame("score3 is not second",score3,testScores[1]);
		assertSame("score1 is not third",score1,testScores[2]);
		
		Score score4 = resTest.saveScore(new InScore("testName4",200));
		assertSame("unable to get score back for test 4",score4,resTest.getScore(score4.getId()));
 
		testScores = resTest.getScores();
		assertEquals("number of top10 scores is not equal to 10",10,testScores.length);
		assertSame("score4 is not first",score4,testScores[0]);
		assertSame("score2 is not second",score2,testScores[1]);
		assertSame("score3 is not third",score3,testScores[2]);
		assertSame("score1 is not fourth",score1,testScores[3]);
	}

}
