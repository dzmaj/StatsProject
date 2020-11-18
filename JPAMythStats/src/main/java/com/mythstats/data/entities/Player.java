package com.mythstats.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "game_player")
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
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
	
	@Column(name = "game_version")
	private Integer gameVersion;
	
	@Column(name = "build_number")
	private Integer buildNumber;
	
	@Column(name = "ip_address")
	private String ipAddress;
	
	
	private boolean host;
	
	private boolean captain;
	
	private boolean dropped;
	
	@Column(name = "units_killed")
	private int unitsKilled;
	
	@Column(name = "units_lost")
	private int unitsLost;
	
	@Column(name = "damage_given")
	private int damageGiven;
	
	@Column(name = "damage_taken")
	private int damageTaken;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user);
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
		builder.append(", gameVersion=");
		builder.append(gameVersion);
		builder.append(", buildNumber=");
		builder.append(buildNumber);
		builder.append(", ipAddress=");
		builder.append(ipAddress);
		builder.append(", host=");
		builder.append(host);
		builder.append(", captain=");
		builder.append(captain);
		builder.append(", dropped=");
		builder.append(dropped);
		builder.append(", unitsKilled=");
		builder.append(unitsKilled);
		builder.append(", unitsLost=");
		builder.append(unitsLost);
		builder.append(", damageGiven=");
		builder.append(damageGiven);
		builder.append(", damageTaken=");
		builder.append(damageTaken);
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
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Player() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Integer getGameVersion() {
		return gameVersion;
	}

	public void setGameVersion(Integer gameVersion) {
		this.gameVersion = gameVersion;
	}

	public Integer getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(Integer buildNumber) {
		this.buildNumber = buildNumber;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public boolean isHost() {
		return host;
	}

	public void setHost(boolean host) {
		this.host = host;
	}

	public boolean isCaptain() {
		return captain;
	}

	public void setCaptain(boolean captain) {
		this.captain = captain;
	}

	public boolean isDropped() {
		return dropped;
	}

	public void setDropped(boolean dropped) {
		this.dropped = dropped;
	}

	public int getUnitsKilled() {
		return unitsKilled;
	}

	public void setUnitsKilled(int unitsKilled) {
		this.unitsKilled = unitsKilled;
	}

	public int getUnitsLost() {
		return unitsLost;
	}

	public void setUnitsLost(int unitsLost) {
		this.unitsLost = unitsLost;
	}

	public int getDamageGiven() {
		return damageGiven;
	}

	public void setDamageGiven(int damageGiven) {
		this.damageGiven = damageGiven;
	}

	public int getDamageTaken() {
		return damageTaken;
	}

	public void setDamageTaken(int damageTaken) {
		this.damageTaken = damageTaken;
	}
}
