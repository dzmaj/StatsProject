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

class TournamentTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Tournament tournament;

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
		tournament = em.find(Tournament.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		tournament = null;
	}
	
	@Test
	@DisplayName("test tournament entity")
	void test1() {
		assertNotNull(tournament);
		assertEquals("test tournament", tournament.getName());
	}
	
	@Test
	@DisplayName("test tournament mapping to tournament teams")
	void test2() {
		assertNotNull(tournament);
		assertNotNull(tournament.getTournamentTeams());
		assertTrue(tournament.getTournamentTeams().size() > 0);
		assertEquals("test team 1", tournament.getTournamentTeams().get(0).getName());
	}
	
	@Test
	@DisplayName("test tournament mapping to tournament matches")
	void test3() {
		assertNotNull(tournament);
		assertNotNull(tournament.getTournamentMatches());
		assertTrue(tournament.getTournamentMatches().size() > 0);
		assertEquals("test match", tournament.getTournamentMatches().get(0).getName());
	}
	
	@Test
	@DisplayName("test tournament mapping to owner")
	void test4() {
		assertNotNull(tournament);
		assertNotNull(tournament.getOwner());
		assertEquals("admin", tournament.getOwner().getUsername());
	}
	

}
