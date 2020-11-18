package com.mythstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mythstats.data.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
	

}
