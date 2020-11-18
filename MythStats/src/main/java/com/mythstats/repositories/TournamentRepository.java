package com.mythstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mythstats.data.entities.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
	

}
