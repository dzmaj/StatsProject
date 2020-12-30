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

class TournamentTeamTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private TournamentTeam tournamentTeam;

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
		tournamentTeam = em.find(TournamentTeam.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		tournamentTeam = null;
	}
	
	@Test
	@DisplayName("test TournamentTeam entity")
	void test1() {
		assertNotNull(tournamentTeam);
		assertEquals("test team 1", tournamentTeam.getName());
	}
	
	@Test
	@DisplayName("test TournamentTeam mapping to TournamentMatch")
	void test2() {
		assertNotNull(tournamentTeam);
		assertNotNull(tournamentTeam.getTournamentMatches());
		assertTrue(tournamentTeam.getTournamentMatches().size() > 0);
		assertEquals("test match", tournamentTeam.getTournamentMatches().get(0).getName());
	}
	
	@Test
	@DisplayName("test TournamentTeam mapping to User")
	void test3() {
		assertNotNull(tournamentTeam);
		assertNotNull(tournamentTeam.getMetaserverUsers());
		assertTrue(tournamentTeam.getMetaserverUsers().size() > 0);
		assertEquals(60, tournamentTeam.getMetaserverUsers().get(0).getId());
	}
	
	@Test
	@DisplayName("test TournamentTeam mapping to Tournament")
	void test4() {
		assertNotNull(tournamentTeam);
		assertNotNull(tournamentTeam.getTournament());
		assertEquals("test tournament", tournamentTeam.getTournament().getName());
	}
	
	@Test
	@DisplayName("test TournamentTeam mapping to owner")
	void test5() {
		assertNotNull(tournamentTeam);
		assertNotNull(tournamentTeam.getOwner());
		assertEquals("admin", tournamentTeam.getOwner().getUsername());
	}
	

}
