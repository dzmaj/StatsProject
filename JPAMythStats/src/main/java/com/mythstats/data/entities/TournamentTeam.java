package com.mythstats.data.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class TournamentTeam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@JsonIgnoreProperties({"tournamentTeams", "tournamentMatches"})
	@ManyToOne
	@JoinColumn(name = "tournament_id")
	private Tournament tournament;
	
	@JsonIgnoreProperties({"tournamentTeam"})
	@OneToMany(mappedBy="tournamentTeam")
	private List<TournamentGameScore> tournamentGameScores;
	
	@JsonIgnoreProperties({"tournamentTeams"})
	@ManyToMany
	@JoinTable(name="tournament_team_has_tournament_match",
	    joinColumns=@JoinColumn(name="tournament_team_id"),
	    inverseJoinColumns=@JoinColumn(name="tournament_match_id")
	)
	private List<TournamentMatch> tournamentMatches;
	
	@JsonIgnoreProperties({"tournamentTeams"})
	@ManyToMany
	@JoinTable(name="metaserver_user_to_tournament_team",
	joinColumns=@JoinColumn(name="tournament_team_id"),
	inverseJoinColumns=@JoinColumn(name="metaserver_user_id"))
	private List<User> metaserverUsers;
	
	@CreationTimestamp
	@Column(name="creation_timestamp")
	private LocalDateTime creationTimestamp;
	
	@UpdateTimestamp
	@Column(name="update_timestamp")
	private LocalDateTime updateTimestamp;
	
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

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
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
		builder.append(", creationTimestamp=");
		builder.append(creationTimestamp);
		builder.append(", updateTimestamp=");
		builder.append(updateTimestamp);
		builder.append(", owner=");
		builder.append(owner);
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

	public List<TournamentMatch> getTournamentMatches() {
		return tournamentMatches;
	}

	public void setTournamentMatches(List<TournamentMatch> tournamentMatches) {
		this.tournamentMatches = tournamentMatches;
	}

	public LocalDateTime getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(LocalDateTime creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public LocalDateTime getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(LocalDateTime updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public SiteUser getOwner() {
		return owner;
	}

	public void setOwner(SiteUser owner) {
		this.owner = owner;
	}

	public List<TournamentGameScore> getTournamentGameScores() {
		return tournamentGameScores;
	}

	public void setTournamentGameScores(List<TournamentGameScore> tournamentGameScores) {
		this.tournamentGameScores = tournamentGameScores;
	}
	
}
