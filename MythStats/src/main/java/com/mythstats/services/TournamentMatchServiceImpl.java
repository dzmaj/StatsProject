package com.mythstats.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mythstats.data.entities.Tournament;
import com.mythstats.data.entities.TournamentMatch;
import com.mythstats.repositories.TournamentMatchRepository;
import com.mythstats.repositories.TournamentRepository;

@Service
public class TournamentMatchServiceImpl implements TournamentMatchService {

	@Autowired
	private TournamentMatchRepository tournMatchRepo;
	@Autowired
	private TournamentRepository tournRepo;
	
	@Override
	public TournamentMatch find(int id, int tournId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TournamentMatch> indexByTournId(int tournId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TournamentMatch create(TournamentMatch tournamentMatch, int tournId) {
		Tournament tourn = tournRepo.findById(tournId).get();
		tourn.addMatch(tournamentMatch);
		tournRepo.save(tourn);
		return tournMatchRepo.saveAndFlush(tournamentMatch);
	}

	@Override
	public TournamentMatch update(TournamentMatch tournamentMatch, int id, int tournId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(int id, int tournId) {
		// TODO Auto-generated method stub
		return null;
	}

}
