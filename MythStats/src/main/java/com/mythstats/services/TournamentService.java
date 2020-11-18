package com.mythstats.services;

import java.util.List;

import com.mythstats.data.entities.Tournament;

public interface TournamentService {
	Tournament find(int id);
	List<Tournament> index();
	Tournament create(Tournament tournament);
	Tournament update(Tournament tournament, int id);
	Boolean delete(int id);
}
