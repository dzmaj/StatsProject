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

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 60);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}
	
	@Test
	@DisplayName("test User entity")
	void test1() {
		assertNotNull(user);
		assertEquals(60, user.getId());
	}
	
	@Test
	@DisplayName("test User mapping to Player")
	void test2() {
		assertNotNull(user);
		assertNotNull(user.getPlayers());
		assertTrue(user.getPlayers().size() > 0);
		assertEquals("hmp", user.getPlayers().get(0).getNickName());
	}
	
	@Test
	@DisplayName("test User mapping to SiteUser")
	void test3() {
		assertNotNull(user);
		assertTrue(user.getSiteUser() == null);
	}
	
	@Test
	@DisplayName("test User mapping to TournamentTeam")
	void test4() {
		assertNotNull(user);
		assertNotNull(user.getTournamentTeams());
		assertEquals("test team 1", user.getTournamentTeams().get(0).getName());
	}
	

}
