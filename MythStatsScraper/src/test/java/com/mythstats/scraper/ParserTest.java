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

class ParserTest {
	private Parser parser;
	private Game game1;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		parser = new Parser();
		parser.parse(1);
		game1 = parser.getGame();
	}

	@AfterEach
	void tearDown() throws Exception {
		parser = null;
		game1 = null;
	}

	@Test
	@DisplayName("test parse game entity")
	void test() {
		assertNotNull(game1);
		assertEquals(1, game1.getId());
		assertEquals("I'll Fall On Your Grave", game1.getMapName());
		System.out.println(game1);
	}

}
