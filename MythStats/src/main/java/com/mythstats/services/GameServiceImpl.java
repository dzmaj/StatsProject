package com.mythstats.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mythstats.data.entities.Game;
import com.mythstats.repositories.GameRepository;
@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameRepository gameRepo;

	@Override
	public Game find(int id) {
		Optional<Game> gameOpt = gameRepo.findById(id);
		if (gameOpt.isPresent()) {
			return gameOpt.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Game> index() {
		return gameRepo.findAll();
	}

	@Override
	public Game create(Game game) {
		game = gameRepo.saveAndFlush(game);
		return game;
	}

	@Override
	public Game update(Game game, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(int id) {
		if (!gameRepo.existsById(id)) {
			return null;
		}
		gameRepo.deleteById(id);
		if (gameRepo.existsById(id)) {
			return false;
		} else {
			return true;
		}
	}

}
