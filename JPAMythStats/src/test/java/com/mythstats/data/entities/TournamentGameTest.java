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

class TournamentGameTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private TournamentGame tournamentGame;

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
		tournamentGame = em.find(TournamentGame.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		tournamentGame = null;
	}
	
	@Test
	@DisplayName("test TournamentGame entity")
	void test1() {
		assertNotNull(tournamentGame);
		assertEquals("test note for tournament game", tournamentGame.getNote());
	}
	
	@Test
	@DisplayName("test TournamentGame mapping to TournamentMatch")
	void test2() {
		assertNotNull(tournamentGame);
		assertNotNull(tournamentGame.getTournamentMatch());
		assertEquals("test match", tournamentGame.getTournamentMatch().getName());
	}
	
	@Test
	@DisplayName("test TournamentGame mapping to Game")
	void test3() {
		assertNotNull(tournamentGame);
		assertNotNull(tournamentGame.getGame());
		assertEquals(16, tournamentGame.getGame().getPlayerCount());
	}
	
	@Test
	@DisplayName("test TournamentGame mapping to TournamentGameScore")
	void test4() {
		assertNotNull(tournamentGame);
		assertNotNull(tournamentGame.getTournamentGameScores());
		assertTrue(tournamentGame.getTournamentGameScores().size() > 0);
		assertEquals(1, tournamentGame.getTournamentGameScores().get(0).getScore());
	}
	

}
