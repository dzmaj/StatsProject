package com.mythstats.data.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="site_user")
public class SiteUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Boolean enabled;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String bio;
	
	@Column(name="profile_pic_url")
	private String profilePicUrl;
	
	private String role;
	
	@JsonIgnoreProperties({"siteUser"})
	@OneToMany(mappedBy="siteUser")
	private List<User> metaserverUsers;
	
	@JsonIgnoreProperties({"tournamentTeams", "tournamentMatches", "owner"})
	@OneToMany(mappedBy="owner")
	private List<Tournament> tournaments;
	
	@JsonIgnoreProperties({"tournamentTeams", "tournamentMatches", "owner"})
	@OneToMany(mappedBy="owner")
	private List<TournamentTeam> tournamentTeams;
	
	@CreationTimestamp
	@Column(name="creation_timestamp")
	private LocalDateTime creationTimestamp;
	
	@UpdateTimestamp
	@Column(name="update_timestamp")
	private LocalDateTime updateTimestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<User> getMetaserverUsers() {
		return metaserverUsers;
	}

	public void setMetaserverUsers(List<User> metaserverUsers) {
		this.metaserverUsers = metaserverUsers;
	}

	public List<Tournament> getTournaments() {
		return tournaments;
	}

	public void setTournaments(List<Tournament> tournaments) {
		this.tournaments = tournaments;
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
		SiteUser other = (SiteUser) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SiteUser [id=");
		builder.append(id);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append(", bio=");
		builder.append(bio);
		builder.append(", profilePicUrl=");
		builder.append(profilePicUrl);
		builder.append(", role=");
		builder.append(role);
		builder.append(", creationTimestamp=");
		builder.append(creationTimestamp);
		builder.append(", updateTimestamp=");
		builder.append(updateTimestamp);
		builder.append("]");
		return builder.toString();
	}

	public SiteUser() {
		super();
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

	public List<TournamentTeam> getTournamentTeams() {
		return tournamentTeams;
	}

	public void setTournamentTeams(List<TournamentTeam> tournamentTeams) {
		this.tournamentTeams = tournamentTeams;
	}

}
