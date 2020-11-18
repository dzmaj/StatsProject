package com.mythstats.services;

import java.util.List;

import com.mythstats.data.entities.Team;

public interface TeamService {
	Team find(int id);
	List<Team> indexByGameId(int gameId);
	Team create(Team team);
	Team update(Team team, int id);
	Boolean delete(int id);
}
