package com.mythstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mythstats.data.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	

}
