/**
 * 
 */
package com.stackroute.favouriteservice.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * @author NishigandhaomanwarOm
 *
 */
@Document(collection = "Match")
public class Match {
	@Id
	private String unique_ID;
	private Date date;
	private String type;
	private String team1;
	private String team2;
	private boolean squad;
	private boolean matchStarted;
	
	public Match(String unique_ID, Date date, String type, String team1, String team2, boolean squad,
			boolean matchStarted) {
		super();
		this.unique_ID = unique_ID;
		this.date = date;
		this.type = type;
		this.team1 = team1;
		this.team2 = team2;
		this.squad = squad;
		this.matchStarted = matchStarted;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public String getUnique_ID() {
		return unique_ID;
	}

	public void setUnique_ID(String unique_ID) {
		this.unique_ID = unique_ID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSquad() {
		return squad;
	}

	public void setSquad(boolean squad) {
		this.squad = squad;
	}
	
	
	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	public boolean isMatchStarted() {
		return matchStarted;
	}

	public void setMatchStarted(boolean matchStarted) {
		this.matchStarted = matchStarted;
	}
	public Match() {}
	
	
}
