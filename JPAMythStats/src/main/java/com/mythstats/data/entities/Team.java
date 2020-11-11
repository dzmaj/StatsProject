package com.mythstats.data.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;
	
	private int place;
	
	@Column(name = "place_tie")
	private boolean placeTie;
	
	private boolean spectators;
	
	private boolean eliminated;
	
	@Column(name = "team_name")
	private String teamName;
	
	@OneToMany(mappedBy = "team")
	private Set<Player> players;

	public Team() {
		super();
	}
	
	public void addPlayer(Player player) {
		if (players == null) {
			players = new HashSet<>();
		}
		players.add(player);
		player.setTeam(this);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Team [id=");
		builder.append(id);
		builder.append(", game=");
		builder.append(game);
		builder.append(", place=");
		builder.append(place);
		builder.append(", placeTie=");
		builder.append(placeTie);
		builder.append(", spectators=");
		builder.append(spectators);
		builder.append(", eliminated=");
		builder.append(eliminated);
		builder.append(", teamName=");
		builder.append(teamName);
		builder.append(", players=");
		builder.append(players);
		builder.append("]");
		return builder.toString();
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
		Team other = (Team) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public boolean isPlaceTie() {
		return placeTie;
	}

	public void setPlaceTie(boolean placeTie) {
		this.placeTie = placeTie;
	}

	public boolean isSpectators() {
		return spectators;
	}

	public void setSpectators(boolean spectators) {
		this.spectators = spectators;
	}

	public boolean isEliminated() {
		return eliminated;
	}

	public void setEliminated(boolean eliminated) {
		this.eliminated = eliminated;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

}
