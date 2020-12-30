package com.mythstats.data.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TournamentMatchTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private TournamentMatch tournamentMatch;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("MythStatsPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		tournamentMatch = em.find(TournamentMatch.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		tournamentMatch = null;
	}
	
	@Test
	@DisplayName("test TournamentMatch entity")
	void test1() {
		assertNotNull(tournamentMatch);
		assertEquals("test match", tournamentMatch.getName());
	}
	
	@Test
	@DisplayName("test TournamentMatch mapping to TournamentGame")
	void test2() {
		assertNotNull(tournamentMatch);
		assertNotNull(tournamentMatch.getTournamentGames());
		assertTrue(tournamentMatch.getTournamentGames().size() > 0);
		assertEquals("test note for tournament game", tournamentMatch.getTournamentGames().get(0).getNote());
	}
	
	@Test
	@DisplayName("test TournamentMatch mapping to TournamentTeam")
	void test3() {
		assertNotNull(tournamentMatch);
		assertNotNull(tournamentMatch.getTournamentTeams());
		assertTrue(tournamentMatch.getTournamentTeams().size() > 0);
		assertEquals("test team 1", tournamentMatch.getTournamentTeams().get(0).getName());
	}
	
	@Test
	@DisplayName("test TournamentMatch mapping to Tournament")
	void test4() {
		assertNotNull(tournamentMatch);
		assertNotNull(tournamentMatch.getTournament());
		assertEquals("test tournament", tournamentMatch.getTournament().getName());
	}
	

}
