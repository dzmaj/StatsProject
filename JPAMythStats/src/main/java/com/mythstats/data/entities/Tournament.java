package com.mythstats.data.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Tournament {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@JsonIgnoreProperties({"tournament"})
	@OneToMany(mappedBy = "tournament")
	private List<TournamentTeam> tournamentTeams;
	
	@JsonIgnoreProperties({"tournament"})
	@OneToMany(mappedBy = "tournament")
	private List<TournamentMatch> tournamentMatches;
	
	@JsonIgnoreProperties({"tournaments", "metaserverUsers", "tournamentTeams"})
	@ManyToOne
	@JoinColumn(name="site_user_id")
	private SiteUser owner;

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

	public Tournament() {
		super();
	}

	public Tournament(int id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tournament [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", owner=");
		builder.append(owner);
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
		Tournament other = (Tournament) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public List<TournamentTeam> getTournamentTeams() {
		return tournamentTeams;
	}

	public void setTournamentTeams(List<TournamentTeam> tournamentTeams) {
		this.tournamentTeams = tournamentTeams;
	}

	public List<TournamentMatch> getTournamentMatches() {
		return tournamentMatches;
	}

	public void setTournamentMatches(List<TournamentMatch> tournamentMatches) {
		this.tournamentMatches = tournamentMatches;
	}
	
	public void addMatch(TournamentMatch match) {
		if (!this.tournamentMatches.contains(match)) {
			this.tournamentMatches.add(match);
			match.setTournament(this);
		}
	}

	public SiteUser getOwner() {
		return owner;
	}

	public void setOwner(SiteUser owner) {
		this.owner = owner;
	}
	

}
