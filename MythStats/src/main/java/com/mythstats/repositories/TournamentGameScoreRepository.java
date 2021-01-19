package com.mythstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mythstats.data.entities.TournamentGameScore;

public interface TournamentGameScoreRepository extends JpaRepository<TournamentGameScore, Integer> {
	

}
