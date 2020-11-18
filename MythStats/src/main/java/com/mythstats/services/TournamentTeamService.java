package com.mythstats.services;

import java.util.List;

import com.mythstats.data.entities.TournamentTeam;

public interface TournamentTeamService {
	TournamentTeam find(int id, int tournId);
	List<TournamentTeam> indexByTournId(int tournId);
	TournamentTeam create(TournamentTeam tournamentTeam, int tournId);
	TournamentTeam update(TournamentTeam tournamentTeam, int id, int tournId);
	Boolean delete(int id, int tournId);
}
