package com.mythstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mythstats.data.entities.Difficulty;

public interface DifficultyRepository extends JpaRepository<Difficulty, Integer> {
	

}
