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

class PlayerTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Player player;

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
		player = em.find(Player.class, 34);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		player = null;
	}
	
//	+----+---------+---------+-----------+-----------+---------------+-----------------+---------------------------+--------------+--------------+------------+------+---------+---------+--------------+------------+--------------+--------------+
//	| id | team_id | user_id | nick_name | team_name | primary_color | secondary_color | coat_of_arms_bitmap_index | game_version | build_number | ip_address | host | captain | dropped | units_killed | units_lost | damage_given | damage_taken |
//	+----+---------+---------+-----------+-----------+---------------+-----------------+---------------------------+--------------+--------------+------------+------+---------+---------+--------------+------------+--------------+--------------+
//	| 34 |      14 |     218 | Shad      | NULL      |          NULL |            NULL |                      NULL |         NULL |         NULL | NULL       |    0 |       1 |       0 |            5 |         17 |           14 |           44 |
//	+----+---------+---------+-----------+-----------+---------------+-----------------+---------------------------+--------------+--------------+------------+------+---------+---------+--------------+------------+--------------+--------------+

	@Test
	@DisplayName("test player entity")
	void test1() {
		assertNotNull(player);
		assertEquals("Shad", player.getNickName());
	}
	
	@Test
	@DisplayName("test player mapping to user")
	void test2() {
		assertNotNull(player);
		assertNotNull(player.getUser());
		assertEquals(218, player.getUser().getId());
	}
	
	@Test
	@DisplayName("test player mapping to team")
	void test3() {
		assertNotNull(player);
		assertNotNull(player.getTeam());
		assertEquals(14, player.getTeam().getId());
	}
	
	

}
