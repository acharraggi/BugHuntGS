package com.mikesilversides.bughuntgs.core;

import com.wordnik.swagger.annotations.*;

@ApiModel( value = "InScore", description = "Input player score resource representation" )
public class InScore {

	//removed 'final' to get dummy constructor to work
	@ApiModelProperty( value = "Player's name", required = true )
    private  String playerName;
	@ApiModelProperty( value = "Player's score", required = true )
    private  long score;

    public InScore(String playerName, long score) {
        this.score = score;
        this.playerName = playerName;
    }
    
    public InScore() {
    	// dummy constructor.
    }
  
    public long getScore() {
        return score;
    }

    public String getPlayerName() {
        return playerName;
    }

}
