package com.mythstats.data.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "metaserver_user")
public class User {

	@Id
	private int id;
	
	@Column(name = "nick_name")
	private String nickName;
	
	@Column(name = "team_name")
	private String teamName;
	
	@Column(name = "primary_color")
	private Integer primaryColor;
	
	@Column(name = "secondary_color")
	private Integer secondaryColor;
	
	@Column(name = "coat_of_arms_bitmap_index")
	private Integer coatOfArmsIndex;
	
	@Column(name = "registration_datetime")
	private LocalDateTime registrationDateTime;
	
	@Column(name = "last_login_datetime")
	private LocalDateTime lastLoginDateTime;
	
	@JsonIgnoreProperties({"gameTeams", "tournament", "metaserverUsers"})
	@ManyToMany(mappedBy = "metaserverUsers")
	private List<TournamentTeam> tournamentTeams;
	
	@JsonIgnoreProperties({"metaserverUsers", "tournaments", "password"})
	@ManyToOne
	@JoinColumn(name="site_user_id")
	private SiteUser siteUser;
	
	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy="user")
	private List<Player> players;

	public User() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", teamName=");
		builder.append(teamName);
		builder.append(", primaryColor=");
		builder.append(primaryColor);
		builder.append(", secondaryColor=");
		builder.append(secondaryColor);
		builder.append(", coatOfArmsIndex=");
		builder.append(coatOfArmsIndex);
		builder.append(", registrationDateTime=");
		builder.append(registrationDateTime);
		builder.append(", lastLoginDateTime=");
		builder.append(lastLoginDateTime);
		builder.append(", siteUser=");
		builder.append(siteUser);
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
		User other = (User) obj;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getPrimaryColor() {
		return primaryColor;
	}

	public void setPrimaryColor(Integer primaryColor) {
		this.primaryColor = primaryColor;
	}

	public Integer getSecondaryColor() {
		return secondaryColor;
	}

	public void setSecondaryColor(Integer secondaryColor) {
		this.secondaryColor = secondaryColor;
	}

	public Integer getCoatOfArmsIndex() {
		return coatOfArmsIndex;
	}

	public void setCoatOfArmsIndex(Integer coatOfArmsIndex) {
		this.coatOfArmsIndex = coatOfArmsIndex;
	}

	public LocalDateTime getRegistrationDateTime() {
		return registrationDateTime;
	}

	public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
		this.registrationDateTime = registrationDateTime;
	}

	public LocalDateTime getLastLoginDateTime() {
		return lastLoginDateTime;
	}

	public void setLastLoginDateTime(LocalDateTime lastLoginDateTime) {
		this.lastLoginDateTime = lastLoginDateTime;
	}

	public List<TournamentTeam> getTournamentTeams() {
		return tournamentTeams;
	}

	public void setTournamentTeams(List<TournamentTeam> tournamentTeams) {
		this.tournamentTeams = tournamentTeams;
	}

	public SiteUser getSiteUser() {
		return siteUser;
	}

	public void setSiteUser(SiteUser siteUser) {
		this.siteUser = siteUser;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
