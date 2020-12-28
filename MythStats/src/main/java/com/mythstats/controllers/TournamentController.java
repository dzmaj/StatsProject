package com.mythstats.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mythstats.data.entities.Tournament;
import com.mythstats.data.entities.TournamentMatch;
import com.mythstats.scraper.Parser;
import com.mythstats.services.GameService;
import com.mythstats.services.PlayerService;
import com.mythstats.services.TournamentMatchService;
import com.mythstats.services.TournamentService;

@CrossOrigin({ "*", "http:localhost:4208" })
@RequestMapping("api")
@RestController
public class TournamentController {
	
	@Autowired
	private GameService gameSvc;
	@Autowired
	private TournamentService tournSvc;
	@Autowired
	private TournamentMatchService tournMatchSvc;
	@Autowired
	private PlayerService playerSvc;
	
	private Parser parser;

	@GetMapping("tournaments/{id}")
	public Tournament find(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer id) {
		Tournament tourn = tournSvc.find(id);
		if (tourn == null) {
			res.setStatus(404);
		}
		return tourn;
	}
	
	@GetMapping("tournaments")
	public List<Tournament> index(HttpServletRequest req, HttpServletResponse res) {
		return tournSvc.index();
	}
	
	@PostMapping("tournaments")
	public Tournament create(HttpServletRequest req, HttpServletResponse res, @RequestBody Tournament tourn) {
		try {
			tourn = tournSvc.create(tourn);
			if (tourn == null) {
				res.setStatus(400);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(tourn.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			tourn = null;
		}
		return tourn;
	}
	
	@PutMapping("tournaments/{id}")
	public Tournament update(HttpServletRequest req, HttpServletResponse res, @RequestBody Tournament tourn, @PathVariable Integer id) {
		try {
			tourn = tournSvc.update(tourn, id);
			if (tourn == null) {
				res.setStatus(400);
			} else {
				res.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			tourn = null;
		}
		return tourn;
	}
	
	@PostMapping("tournaments/{id}/matches")
	public TournamentMatch addMatch(HttpServletRequest req, HttpServletResponse res, @RequestBody TournamentMatch tournMatch, @PathVariable Integer id) {
		try {
			tournMatch = tournMatchSvc.create(tournMatch, id);
			if (tournMatch == null) {
				res.setStatus(400);
			} else {
				res.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			tournMatch = null;
		}
		return tournMatch;
	}
	
}
