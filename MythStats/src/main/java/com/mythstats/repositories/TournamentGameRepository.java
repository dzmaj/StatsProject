package com.mythstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mythstats.data.entities.TournamentGame;

public interface TournamentGameRepository extends JpaRepository<TournamentGame, Integer> {
	

}
