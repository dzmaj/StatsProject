package com.mythstats.data.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class TournamentTeam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "tournament_id")
	private Tournament tournament;
	
	@ManyToMany
	@JoinTable(name="game_team_to_tournament_team",
	    joinColumns=@JoinColumn(name="tournament_team_id"),
	    inverseJoinColumns=@JoinColumn(name="team_id")
	)
	private List<Team> gameTeams;
	
	@ManyToMany
	@JoinTable(name="metaserver_user_to_tournament_team",
	joinColumns=@JoinColumn(name="tournament_team_id"),
	inverseJoinColumns=@JoinColumn(name="metaserver_user_id"))
	private List<User> metaserverUsers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public List<Team> getGameTeams() {
		return gameTeams;
	}

	public void setGameTeams(List<Team> gameTeams) {
		this.gameTeams = gameTeams;
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
		TournamentTeam other = (TournamentTeam) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TournamentTeam [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", tournament=");
		builder.append(tournament);
		builder.append("]");
		return builder.toString();
	}

	public TournamentTeam() {
		super();
	}

	public TournamentTeam(int id) {
		super();
		this.id = id;
	}

	public List<User> getMetaserverUsers() {
		return metaserverUsers;
	}

	public void setMetaserverUsers(List<User> metaserverUsers) {
		this.metaserverUsers = metaserverUsers;
	}
	
}
