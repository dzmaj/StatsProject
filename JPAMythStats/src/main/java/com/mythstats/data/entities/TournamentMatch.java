package com.mythstats.data.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class TournamentMatch {
	@Id
	@GeneratedValue
	private int id;

	private String name;

	private String description;

	@JsonIgnoreProperties({"tournamentMatches", "tournamentTeams"})
	@ManyToOne
	@JoinColumn(name = "tournament_id")
	private Tournament tournament;
	
	@ManyToMany
	@JoinTable(name = "tournament_match_to_game",
		joinColumns = @JoinColumn(name = "game_id"),
		inverseJoinColumns = @JoinColumn(name = "tournament_match_id"))
	private List<Game> games;
	
	@ManyToMany(mappedBy="tournamentMatches")
	private List<TournamentTeam> tournamentTeams;
	
	@UpdateTimestamp
	@Column(name="update_timestamp")
	private LocalDateTime updateTimestamp;
	
	@Column(name="scheduled_time")
	private LocalDateTime scheduledTime;

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

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
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
		TournamentMatch other = (TournamentMatch) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TournamentMatch [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", tournament=");
		builder.append(tournament);
		builder.append(", updateTimestamp=");
		builder.append(updateTimestamp);
		builder.append(", scheduledTime=");
		builder.append(scheduledTime);
		builder.append("]");
		return builder.toString();
	}

	public TournamentMatch() {
		super();
	}

	public TournamentMatch(int id) {
		super();
		this.id = id;
	}

	public List<TournamentTeam> getTournamentTeams() {
		return tournamentTeams;
	}

	public void setTournamentTeams(List<TournamentTeam> tournamentTeams) {
		this.tournamentTeams = tournamentTeams;
	}

	public LocalDateTime getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(LocalDateTime updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public LocalDateTime getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(LocalDateTime scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	
	
}
