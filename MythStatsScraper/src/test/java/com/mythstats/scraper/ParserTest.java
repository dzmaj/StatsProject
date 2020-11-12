package com.mythstats.scraper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mythstats.data.entities.Game;
import com.mythstats.data.entities.Player;
import com.mythstats.data.entities.Team;

class ParserTest {
	private Parser parser;
	private Game game;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		parser = new Parser();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		parser = null;
		game = null;
	}

	@Test
	@DisplayName("test parse game entity")
	void game1Test1() {
		parser.parse(1);
		game = parser.getGame();
		assertNotNull(game);
		assertEquals(1, game.getId());
		assertEquals("I'll Fall On Your Grave", game.getMapName());
	}
	@Test
	@DisplayName("test parse game teams")
	void game1Test2() {
		parser.parse(1);
		game = parser.getGame();
		assertNotNull(game);
		assertNotNull(game.getTeams());
		assertEquals(4, game.getTeams().size());
	}
	
	@Test
	@DisplayName("test parse game entity")
	void game298186Test1() {
		parser.parse(298186);
		game = parser.getGame();
		assertNotNull(game);
		assertEquals(298186, game.getId());
		assertEquals("Killing Grounds", game.getMapName());
	}
	@Test
	@DisplayName("test parse game teams")
	void game298186Test2() {
		parser.parse(298186);
		game = parser.getGame();
		assertNotNull(game);
		assertNotNull(game.getTeams());
		assertEquals(3, game.getTeams().size());
	}
	
	@Test
	@DisplayName("test parse game entity")
	void game306144Test1() {
		parser.parse(306144);
		game = parser.getGame();
		assertNotNull(game);
		assertEquals(306144, game.getId());
		assertEquals("Raisin Barn |i(Light)", game.getMapName());
		assertEquals("Territories", game.getGametype().getName());
	}
	@Test
	@DisplayName("test parse game teams")
	void game306144Test2() {
		parser.parse(306144);
		game = parser.getGame();
		assertNotNull(game);
		assertNotNull(game.getTeams());
		assertEquals(2, game.getTeams().size());
		System.out.println(game);
		for (Team t : game.getTeams()) {
			System.out.println(t);
			for (Player p : t.getPlayers()) {
				System.out.println(p);
			}
		}
	}
	

}
