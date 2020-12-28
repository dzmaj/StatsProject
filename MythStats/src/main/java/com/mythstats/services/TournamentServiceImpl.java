package com.mythstats.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mythstats.data.entities.Tournament;
import com.mythstats.repositories.TournamentRepository;

@Service
public class TournamentServiceImpl implements TournamentService {
	
	@Autowired
	private TournamentRepository tournRepo;


	@Override
	public Tournament find(int id) {
		Optional<Tournament> tournOpt = tournRepo.findById(id);
		return tournOpt.isPresent() ? tournOpt.get() : null;
	}

	@Override
	public List<Tournament> index() {	
		return tournRepo.findAll();
	}

	@Override
	public Tournament create(Tournament tournament) {
		return tournRepo.saveAndFlush(tournament);
	}

	@Override
	public Tournament update(Tournament tournament, int id) {
		if (tournament.getId() != id) {
			return null;
		}
		if (tournRepo.existsById(id)) {
			return null;
		}
		Tournament dbTourn = tournRepo.findById(id).get();
		if (tournament.getName() != null) {
			dbTourn.setName(tournament.getName());
		}
		if (tournament.getDescription() != null) {
			dbTourn.setDescription(tournament.getDescription());
		}
		return tournRepo.saveAndFlush(dbTourn);
	}

	@Override
	public Boolean delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
