package com.mythstats.services;

import java.util.List;

import com.mythstats.data.entities.Game;

public interface GameService {
	Game find(int id);
	List<Game> index();
	Game create(Game game);
	Game update(Game game, int id);
	Boolean delete(int id);
	Game createFromGoS(Game game);
}
