package com.mythstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mythstats.data.entities.TournamentMatch;

public interface TournamentMatchRepository extends JpaRepository<TournamentMatch, Integer> {
	

}
