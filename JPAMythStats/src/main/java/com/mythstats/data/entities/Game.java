package com.mythstats.data.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Game {
	
	@Id
	private int id;
	
	@Column(name = "team_count")
	private int teamCount;
	
	@Column(name = "player_count")
	private int playerCount;
	
	// null
	@Column(name = "game_name")
	private String gameName;
	
	@Column(name = "map_name")
	private String mapName;
	
	@ManyToOne
	@JoinColumn(name = "gametype_id")
	private Gametype gametype;
	
	@ManyToOne
	@JoinColumn(name = "difficulty_id")
	private Difficulty difficulty;
	
	// Number of seconds?
	@Column(name = "time_limit")
	private Integer timeLimit;
	
	@Column(name = "planning_time_limit")
	private Integer planningTimeLimit;
	
	// TODO: coop logic
	private boolean cooperative;
	
	@Column(name = "allow_teams")
	private boolean allowTeams;
	
	@Column(name = "allow_unit_trading")
	private boolean allowUnitTrading;
	
	@Column(name = "allow_veterans")
	private boolean allowVeterans;
	
	@Column(name = "allow_alliances")
	private boolean allowAlliances;
	
	@Column(name = "overhead_map")
	private boolean overheadMap;
	
	private boolean deathmatch;
	
	@Column(name = "vtfl")
	private boolean vTFL;
	
	@Column(name = "anti_clump")
	private boolean antiClump;
	
	@Column(name = "end_datetime")
	private LocalDateTime endDateTime;
	
	@Column(name = "start_datetime")
	private LocalDateTime startDateTime;
	
	@Column(name = "ended_code")
	private Integer endedCode;
	
	private int duration;
	
	@Column(name = "recording_url")
	private String recordingURL;
	
	@JsonIgnoreProperties({"game", "tournamentGameScores"})
	@OneToMany(mappedBy = "game")
	private List<Team> teams;
	
	@JsonIgnoreProperties({"game", "tournamentGameScores", "tournamentMatch"})
	@ManyToMany(mappedBy = "game")
	private List<TournamentGame> tournamentGames;

	public Game() {
		super();
	}
	
	public void addTeam(Team team) {
		if (teams == null) {
			teams = new ArrayList<>();
		}
		teams.add(team);
		team.setGame(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Game [id=");
		builder.append(id);
		builder.append(", teamCount=");
		builder.append(teamCount);
		builder.append(", playerCount=");
		builder.append(playerCount);
		builder.append(", gameName=");
		builder.append(gameName);
		builder.append(", mapName=");
		builder.append(mapName);
		builder.append(", gametype=");
		builder.append(gametype);
		builder.append(", difficulty=");
		builder.append(difficulty);
		builder.append(", timeLimit=");
		builder.append(timeLimit);
		builder.append(", planningTimeLimit=");
		builder.append(planningTimeLimit);
		builder.append(", cooperative=");
		builder.append(cooperative);
		builder.append(", allowTeams=");
		builder.append(allowTeams);
		builder.append(", allowUnitTrading=");
		builder.append(allowUnitTrading);
		builder.append(", allowVeterans=");
		builder.append(allowVeterans);
		builder.append(", allowAlliances=");
		builder.append(allowAlliances);
		builder.append(", overheadMap=");
		builder.append(overheadMap);
		builder.append(", deathmatch=");
		builder.append(deathmatch);
		builder.append(", vTFL=");
		builder.append(vTFL);
		builder.append(", antiClump=");
		builder.append(antiClump);
		builder.append(", endDateTime=");
		builder.append(endDateTime);
		builder.append(", startDateTime=");
		builder.append(startDateTime);
		builder.append(", endedCode=");
		builder.append(endedCode);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", recordingURL=");
		builder.append(recordingURL);
		builder.append("]");
		return builder.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeamCount() {
		return teamCount;
	}

	public void setTeamCount(int teamCount) {
		this.teamCount = teamCount;
	}


	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public Gametype getGametype() {
		return gametype;
	}

	public void setGametype(Gametype gametype) {
		this.gametype = gametype;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getPlanningTimeLimit() {
		return planningTimeLimit;
	}

	public void setPlanningTimeLimit(Integer planningTimeLimit) {
		this.planningTimeLimit = planningTimeLimit;
	}

	public boolean isCooperative() {
		return cooperative;
	}

	public void setCooperative(boolean cooperative) {
		this.cooperative = cooperative;
	}

	public boolean isAllowTeams() {
		return allowTeams;
	}

	public void setAllowTeams(boolean allowTeams) {
		this.allowTeams = allowTeams;
	}

	public boolean isAllowUnitTrading() {
		return allowUnitTrading;
	}

	public void setAllowUnitTrading(boolean allowUnitTrading) {
		this.allowUnitTrading = allowUnitTrading;
	}

	public boolean isAllowVeterans() {
		return allowVeterans;
	}

	public void setAllowVeterans(boolean allowVeterans) {
		this.allowVeterans = allowVeterans;
	}

	public boolean isAllowAlliances() {
		return allowAlliances;
	}

	public void setAllowAlliances(boolean allowAlliances) {
		this.allowAlliances = allowAlliances;
	}

	public boolean isOverheadMap() {
		return overheadMap;
	}

	public void setOverheadMap(boolean overheadMap) {
		this.overheadMap = overheadMap;
	}

	public boolean isDeathmatch() {
		return deathmatch;
	}

	public void setDeathmatch(boolean deathmatch) {
		this.deathmatch = deathmatch;
	}

	public boolean isvTFL() {
		return vTFL;
	}

	public void setvTFL(boolean vTFL) {
		this.vTFL = vTFL;
	}

	public boolean isAntiClump() {
		return antiClump;
	}

	public void setAntiClump(boolean antiClump) {
		this.antiClump = antiClump;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Integer getEndedCode() {
		return endedCode;
	}

	public void setEndedCode(Integer endedCode) {
		this.endedCode = endedCode;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getRecordingURL() {
		return recordingURL;
	}

	public void setRecordingURL(String recordingURL) {
		this.recordingURL = recordingURL;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	public List<TournamentGame> getTournamentGames() {
		return tournamentGames;
	}

	public void setTournamentGames(List<TournamentGame> tournamentGames) {
		this.tournamentGames = tournamentGames;
	}

	
}
