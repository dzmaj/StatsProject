package com.mythstats.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class TournamentGameScore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Integer score;
	
	@JsonIgnoreProperties({"tournamentGameScores"})
	@ManyToOne
	@JoinColumn(name="tournament_team_id")
	private TournamentTeam tournamentTeam;
	
	@JsonIgnoreProperties({"tournamentGameScores"})
	@ManyToOne
	@JoinColumn(name="game_team_id")
	private Team team;
	
	@JsonIgnoreProperties({"tournamentGameScores"})
	@ManyToOne
	@JoinColumn(name="tournament_game_id")
	private TournamentGame tournamentGame;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public TournamentTeam getTournamentTeam() {
		return tournamentTeam;
	}

	public void setTournamentTeam(TournamentTeam tournamentTeam) {
		this.tournamentTeam = tournamentTeam;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public TournamentGame getTournamentGame() {
		return tournamentGame;
	}

	public void setTournamentGame(TournamentGame tournamentGame) {
		this.tournamentGame = tournamentGame;
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
		TournamentGameScore other = (TournamentGameScore) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TournamentGameScore [id=");
		builder.append(id);
		builder.append(", score=");
		builder.append(score);
		builder.append(", tournamentTeam=");
		builder.append(tournamentTeam);
		builder.append(", team=");
		builder.append(team);
		builder.append(", tournamentGame=");
		builder.append(tournamentGame);
		builder.append("]");
		return builder.toString();
	}

	public TournamentGameScore() {
		super();
	}

}
