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

class TeamTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Team team;

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
		team = em.find(Team.class, 14);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		team = null;
	}
	
//	mysql> select * from game_team where id = 14;
//	+----+---------+-------+-----------+------------+------------+----------------+
//	| id | game_id | place | place_tie | spectators | eliminated | team_name      |
//	+----+---------+-------+-----------+------------+------------+----------------+
//	| 14 |  326507 |     1 |         0 |          0 |          0 | Doomsday squad |
//	+----+---------+-------+-----------+------------+------------+----------------+

	@Test
	@DisplayName("test team entity")
	void test1() {
		assertNotNull(team);
		assertEquals("Doomsday squad", team.getTeamName());
	}
	
	@Test
	@DisplayName("test team mapping to players")
	void test2() {
		assertNotNull(team);
		assertNotNull(team.getPlayers());
		assertEquals(8, team.getPlayers().size());
	}
	
	@Test
	@DisplayName("test team mapping to game")
	void test3() {
		assertNotNull(team);
		assertNotNull(team.getGame());
		assertEquals(326507, team.getGame().getId());
	}
	
	

}
