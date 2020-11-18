package com.mythstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mythstats.data.entities.TournamentTeam;

public interface TournamentTeamRepository extends JpaRepository<TournamentTeam, Integer> {
	

}
