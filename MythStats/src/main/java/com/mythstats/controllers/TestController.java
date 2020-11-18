package com.mythstats.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mythstats.data.entities.Game;
import com.mythstats.scraper.Parser;
import com.mythstats.services.GameService;
import com.mythstats.services.PlayerService;
import com.mythstats.services.TeamService;

@RequestMapping("api/test")
@RestController
public class TestController {
	
	@Autowired
	private GameService gameSvc;
	@Autowired
	private TeamService teamSvc;
	@Autowired
	private PlayerService playerSvc;
	
	private Parser parser;
	
	@GetMapping("ping")
	public String pingPong() {
		return "pong";
	}
	
	@GetMapping("gos/{id}")
	public Game getOrParse(@PathVariable Integer id) {
		Game game = null;
		game = gameSvc.find(id);
		if (game == null) {
			parser = new Parser();
			game = parser.parse(id);
			if (game != null) {
				game = gameSvc.createFromGoS(game);
			}
		}
		
		
		return game;
	}
	
	
}
