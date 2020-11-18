package com.mythstats.services;

import java.util.List;

import com.mythstats.data.entities.TournamentMatch;

public interface TournamentMatchService {
	TournamentMatch find(int id, int tournId);
	List<TournamentMatch> indexByTournId(int tournId);
	TournamentMatch create(TournamentMatch tournamentMatch, int tournId);
	TournamentMatch update(TournamentMatch tournamentMatch, int id, int tournId);
	Boolean delete(int id, int tournId);
}
