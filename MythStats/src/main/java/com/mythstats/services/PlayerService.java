package com.mythstats.services;

import java.util.List;

import com.mythstats.data.entities.Player;

public interface PlayerService {
	Player find(int id);
	List<Player> indexByTeamId(int teamId);
	Player create(Player player);
	Player update(Player player, int id);
	Boolean delete(int id);
}
