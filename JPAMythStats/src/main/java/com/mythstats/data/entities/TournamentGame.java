package com.mythstats.data.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tournament_game")
public class TournamentGame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String note;
	
	@JsonIgnoreProperties({"tournamentGames"})
	@ManyToOne
	@JoinColumn(name="tournament_match_id")
	private TournamentMatch tournamentMatch;
	
	@JsonIgnoreProperties({"tournamentGames"})
	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;
	
	@JsonIgnoreProperties({"tournamentGame"})
	@OneToMany(mappedBy="tournamentGame")
	private List<TournamentGameScore> tournamentGameScores;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public TournamentMatch getTournamentMatch() {
		return tournamentMatch;
	}

	public void setTournamentMatch(TournamentMatch tournamentMatch) {
		this.tournamentMatch = tournamentMatch;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<TournamentGameScore> getTournamentGameScores() {
		return this.tournamentGameScores;
	}

	public void setTournamentGameScores(List<TournamentGameScore> tournamentGameScores) {
		this.tournamentGameScores = tournamentGameScores;
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
		TournamentGame other = (TournamentGame) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TournamentGame [id=");
		builder.append(id);
		builder.append(", note=");
		builder.append(note);
		builder.append(", tournamentMatch=");
		builder.append(tournamentMatch);
		builder.append(", game=");
		builder.append(game);
		builder.append("]");
		return builder.toString();
	}

	public TournamentGame() {
		super();
	}
}
