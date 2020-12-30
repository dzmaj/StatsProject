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

class GameTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Game game;

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
		game = em.find(Game.class, 326507);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		game = null;
	}

	@Test
	@DisplayName("test game entity")
	void test1() {
//		mysql> select * from game where id = 326507;
//		+--------+-----------+------------+--------------+-----------+-------------------------------+-------------+---------------+------------+---------------------+-------------+-------------+--------------------+----------------+-----------------+--------------+------------+------+------------+---------------------+---------------------+------------+----------+---------------+
//		| id     | room_type | team_count | player_count | game_name | map_name                      | gametype_id | difficulty_id | time_limit | planning_time_limit | cooperative | allow_teams | allow_unit_trading | allow_veterans | allow_alliances | overhead_map | deathmatch | vtfl | anti_clump | end_datetime        | start_datetime      | ended_code | duration | recording_url |
//		+--------+-----------+------------+--------------+-----------+-------------------------------+-------------+---------------+------------+---------------------+-------------+-------------+--------------------+----------------+-----------------+--------------+------------+------+------------+---------------------+---------------------+------------+----------+---------------+
//		| 326507 |      NULL |          2 |           16 | NULL      | Barbarian Valley T.E. - Light |           4 |             4 |      21600 |                5400 |           0 |           1 |                  1 |              0 |               0 |            1 |          0 |    0 |          0 | 2020-11-21 15:04:08 | 2020-11-21 14:54:52 |       NULL |    16680 | NULL          |
//		+--------+-----------+------------+--------------+-----------+-------------------------------+-------------+---------------+------------+---------------------+-------------+-------------+--------------------+----------------+-----------------+--------------+------------+------+------------+---------------------+---------------------+------------+----------+---------------+
		assertNotNull(game);
		assertEquals("Barbarian Valley T.E. - Light", game.getMapName());
	}
	
	@Test
	@DisplayName("test game mapping to team")
	void test2() {
//		mysql> select * from game_team where game_id = 326507;
//		+----+---------+-------+-----------+------------+------------+------------------+
//		| id | game_id | place | place_tie | spectators | eliminated | team_name        |
//		+----+---------+-------+-----------+------------+------------+------------------+
//		| 14 |  326507 |     1 |         0 |          0 |          0 | Doomsday squad   |
//		| 15 |  326507 |     2 |         0 |          0 |          0 | |iP-Familiarness |
//		+----+---------+-------+-----------+------------+------------+------------------+
		assertNotNull(game);
		assertNotNull(game.getTeams());
		assertEquals(2, game.getTeams().size());
	}
	
	

}
