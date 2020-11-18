package com.mythstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mythstats.data.entities.Gametype;

public interface GametypeRepository extends JpaRepository<Gametype, Integer> {
	

}
