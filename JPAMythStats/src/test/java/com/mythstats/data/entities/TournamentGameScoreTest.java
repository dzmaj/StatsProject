package com.mythstats.data.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TournamentGameScoreTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private TournamentGameScore tournamentGameScore;

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
		tournamentGameScore = em.find(TournamentGameScore.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		tournamentGameScore = null;
	}
	
	@Test
	@DisplayName("test TournamentGameScore entity")
	void test1() {
		assertNotNull(tournamentGameScore);
		assertEquals(1, tournamentGameScore.getScore());
	}
	
	@Test
	@DisplayName("test TournamentGameScore mapping to Team")
	void test2() {
		assertNotNull(tournamentGameScore);
		assertNotNull(tournamentGameScore.getTeam());
		assertEquals(14, tournamentGameScore.getTeam().getId());
	}
	
	@Test
	@DisplayName("test TournamentGameScore mapping to TournamentGame")
	void test3() {
		assertNotNull(tournamentGameScore);
		assertNotNull(tournamentGameScore.getTournamentGame());
		assertEquals("test note for tournament game", tournamentGameScore.getTournamentGame().getNote());
	}
	
	@Test
	@DisplayName("test TournamentGameScore mapping to TournamentTeam")
	void test4() {
		assertNotNull(tournamentGameScore);
		assertNotNull(tournamentGameScore.getTournamentTeam());
		assertEquals("test team 1", tournamentGameScore.getTournamentTeam().getName());
	}

	

}
