package com.mikesilversides.bughuntgs.core;

//import com.wordnik.swagger.annotations.*;

//@ApiModel( value = "Score", description = "Player score resource representation" )
public class Score implements Comparable<Score> {

	//@ApiModelProperty( value = "Player's score id", required = true )
	private final long id;
	//@ApiModelProperty( value = "Player's name", required = true )
    private final String playerName;
	//@ApiModelProperty( value = "Player's score", required = true )
    private final long theScore;

    public Score(long id, String playerName, long score) {
        this.id = id;
        this.theScore = score;
        this.playerName = playerName;
    }
    
    public long getId() {
        return id;
    }
    
    public long getScore() {
        return theScore;
    }

    public String getPlayerName() {
        return playerName;
    }
	public int compareTo(Score compareScore) {	 
		return (int) ( compareScore.getScore() - this.getScore() );
	}
}
