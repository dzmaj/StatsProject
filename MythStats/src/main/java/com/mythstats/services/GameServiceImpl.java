package com.mythstats.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mythstats.data.entities.Game;
import com.mythstats.data.entities.Player;
import com.mythstats.data.entities.Team;
import com.mythstats.data.entities.User;
import com.mythstats.repositories.GameRepository;
import com.mythstats.repositories.PlayerRepository;
import com.mythstats.repositories.TeamRepository;
import com.mythstats.repositories.UserRepository;
@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameRepository gameRepo;
	@Autowired 
	private TeamRepository teamRepo;
	@Autowired
	private PlayerRepository playerRepo;
	@Autowired
	private UserRepository userRepo;
	
	

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

	@Override
	public Game createFromGoS(Game game) {
		Game dbGame = gameRepo.saveAndFlush(game);
		List<Team> teams = game.getTeams();
		game.setTeams(null);
		dbGame.setTeams(null);
		for (Team team : teams) {
			Team dbTeam = teamRepo.save(team);
			List<Player> players = team.getPlayers();
			team.setPlayers(null);
			dbTeam.setPlayers(null);
			for (int i = 0; i < players.size(); i++) {
				Player player = players.get(i);
				User user = player.getUser();
				if (user != null) {
					user = userRepo.saveAndFlush(user);
					player.setUser(user);
				}
				dbTeam.addPlayer(player);
				Player dbplayer = playerRepo.saveAndFlush(player);
			}
			dbGame.addTeam(dbTeam);
		}
		dbGame = gameRepo.saveAndFlush(dbGame);
		return dbGame;
	}

}
