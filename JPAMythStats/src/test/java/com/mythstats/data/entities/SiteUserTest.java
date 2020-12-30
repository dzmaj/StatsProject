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

class SiteUserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private SiteUser siteUser;

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
		siteUser = em.find(SiteUser.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		siteUser = null;
	}
	
	@Test
	@DisplayName("test SiteUser entity")
	void test1() {
		assertNotNull(siteUser);
		assertEquals("admin", siteUser.getUsername());
	}
	
	@Test
	@DisplayName("test SiteUser mapping to Tournament")
	void test2() {
		assertNotNull(siteUser);
		assertNotNull(siteUser.getTournaments());
		assertTrue(siteUser.getTournaments().size() > 0);
		assertEquals("test tournament", siteUser.getTournaments().get(0).getName());
	}
	
	@Test
	@DisplayName("test SiteUser mapping to User")
	void test3() {
		assertNotNull(siteUser);
		assertNotNull(siteUser.getMetaserverUsers());
		assertTrue(siteUser.getMetaserverUsers().size() == 0);
	}
	
	@Test
	@DisplayName("test SiteUser mapping to TournamentTeam")
	void test4() {
		assertNotNull(siteUser);
		assertNotNull(siteUser.getTournamentTeams());
		assertEquals("test team 1", siteUser.getTournamentTeams().get(0).getName());
	}
	

}
