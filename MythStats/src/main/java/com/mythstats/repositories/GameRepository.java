package com.mythstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mythstats.data.entities.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {
	

}
