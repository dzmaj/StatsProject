package com.mythstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mythstats.data.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
	

}
