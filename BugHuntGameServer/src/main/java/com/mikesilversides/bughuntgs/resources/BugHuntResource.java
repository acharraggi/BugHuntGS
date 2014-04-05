package com.mikesilversides.bughuntgs.resources;

import com.mikesilversides.bughuntgs.core.InScore;
import com.mikesilversides.bughuntgs.core.Score;
import com.mikesilversides.bughuntgs.core.Scores;

//import com.mikesilversides.bughuntgs.core.*;

import com.codahale.metrics.annotation.Timed;


import javax.ws.rs.*;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wordnik.swagger.annotations.*;

@Path("/scores")
@Api( value = "/scores", description = "Manage top 10 player scores" )
@Produces(MediaType.APPLICATION_JSON)
public class BugHuntResource {
	final static Logger logger = LoggerFactory.getLogger(BugHuntResource.class);
	
    private /*final*/ AtomicLong counter;
    private /*final*/ long score;
    private /*final*/ String playerName;
    
    private Scores theScores /*= new Scores()*/;
    
    public BugHuntResource(String playerName, long score) {
    	this.counter = new AtomicLong();
        this.score = score;
        this.playerName = playerName;
    }
    
    public BugHuntResource(Scores inScores) {
    	this.counter = new AtomicLong();
    	theScores = inScores;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    @ApiOperation(value = "Save player score", 
       notes = "Save a player score. Returns the score with an id assigned. Currently implemented as an in memory array.", 
       response = Score.class )
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid input field. Only playerName and score are valid.") })
    public Score saveScore( @ApiParam(value = "The score to be saved.", required = true)  
    						InScore iScore) {
    	logger.debug("saveScore: playerName="+iScore.getPlayerName());
    	Score newScore = new Score(counter.incrementAndGet(), iScore.getPlayerName(), iScore.getScore());
    	theScores.putScore(newScore);
        return   newScore;
    }
    
    @GET
    @Timed
    @ApiOperation(value = "Get top 10 scores.", 
    	notes = "Returns an array of the top 10 player scores.", 
    	response = Score.class,
    	responseContainer = "List")
    public Score[] getScores() {
    	logger.debug("getScores: number of scores returned="+theScores.top10().length);
        return   theScores.top10();
    }
    
    /**
     * get Score by id, only for unit testing, not exposed in the API.
     * @param id
     * @return Score
     */
    public Score getScore(long id) {
        return   theScores.getScore(id);
    }

}
