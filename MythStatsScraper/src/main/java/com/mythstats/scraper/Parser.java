package com.mythstats.scraper;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.mythstats.data.entities.Difficulty;
import com.mythstats.data.entities.Game;
import com.mythstats.data.entities.Gametype;

public class Parser {
	
	private static final String URL_BASE = "http://gateofstorms.net/games/";
	private Element content;
	private Game game;
	
	public Game parse(int gameId) {
		game = null;
		String url = URL_BASE + gameId;
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (doc == null) {
			return null;
		} else {
			content = doc.getElementById("content");
		}
		game = new Game();
		game.setId(gameId);
		parseGameMeta1();
		parseGameMeta2();
		
		return game;
	}

	private void parseGameMeta1() {
		Element metaPart1 = content.getElementsByClass("medium-9").first();
		String tempStr = metaPart1.selectFirst("h3").text();
		String[] strArr;
		String gameType;
		String gameMesh;
		if (tempStr.contains("Last Man on the Hill")) {
			gameType = "Last Man on the Hill";
			gameMesh = tempStr.substring(24);
		}
		else if (tempStr.contains("Balls on Parade")){
			gameType = "Balls on Parade";
			gameMesh = tempStr.substring(19);
		}
		else {
			strArr = tempStr.split(" on ", 2);
			gameType = strArr[0];
			gameMesh = strArr[1];
		}

		
		tempStr = metaPart1.selectFirst("p").html().replaceAll("<p>", "");
		strArr = tempStr.split("<br> Began at ");
		String gameHost = strArr[0].replaceAll("Hosted by ", "");
		String gameStartTimeStr = strArr[1].split(" and lasted for ")[0];
		String gameRealDurationStr = strArr[1].split(" and lasted for ")[1];
		tempStr = null;
		strArr = null;
		
		game.setMapName(gameMesh);
		game.setGametype(parseGametype(gameType));
		gameStartTimeStr = gameStartTimeStr.replace(' ', 'T');
		game.setStartDateTime(LocalDateTime.parse(gameStartTimeStr));
		LocalTime t = LocalTime.parse(gameRealDurationStr);
		Duration d = Duration.between(LocalTime.of(0, 0), t);
		LocalDateTime endDateTime = game.getStartDateTime().plusSeconds(d.getSeconds());
		game.setEndDateTime(endDateTime);
//		g.setHost(gameHost);
	}
	
	private Gametype parseGametype(String gameType) {
		Gametype gametype = null;
		switch (gameType) {
		case "Body Count":
			gametype = new Gametype(0, "Body Count");
			break;
		case "Steal the Bacon":
			gametype = new Gametype(1, "Steal the Bacon");
			break;
		case "Last Man on the Hill":
			gametype = new Gametype(2, "Last Man on the Hill");
			break;
		case "Scavenger Hunt":
			gametype = new Gametype(3, "Scavenger Hunt");
			break;
		case "Flag Rally":
			gametype = new Gametype(4, "Flag Rally");
			break;
		case "Capture the Flag":
			gametype = new Gametype(5, "Capture the Flag");
			break;
		case "Balls on Parade":
			gametype = new Gametype(6, "Balls on Parade");
			break;
		case "Territories":
			gametype = new Gametype(7, "Territories");
			break;
		case "Captures":
			gametype = new Gametype(8, "Captures");
			break;
		case "King of the Hill":
			gametype = new Gametype(9, "King of the Hill");
			break;
		case "Stampede":
			gametype = new Gametype(10, "Stampede");
			break;
		case "Assassin":
			gametype = new Gametype(11, "Assassin");
			break;
		case "Hunting":
			gametype = new Gametype(12, "Hunting");
			break;
		case "Co-op":
			gametype = new Gametype(13, "Co-op");
			break;
		case "King of the Hill (TFL)":
			gametype = new Gametype(14, "King of the Hill (TFL)");
			break;
		case "King of the Map":
			gametype = new Gametype(15, "King of the Map");
			break;
		default:
			break;
		}
		return gametype;
	}

	private void parseGameMeta2() {
		String settingsStr;
		Duration d;
		LocalTime t;
		Element metaPart2 = content.getElementsByClass("medium-3").first().child(1);
		settingsStr = metaPart2.html();
		System.out.println(settingsStr);
		String regex = "(\\d{2}:\\d{2}:\\d{2})";
//		Pattern p = Pattern.compile(regex);
//	    Matcher m = p.matcher(settingsStr);
//		if (settingsStr.contains("Unit Trading Enabled")) {
//			g.setTradingEnabled(true);
//		}
//		if (!settingsStr.contains("No Alliances")) {
//			g.setAlliesAllowed(true);
//		}
//		if (settingsStr.contains("Teams Allowed")) {
//			g.setTeamsAllowed(true);
//		}
//		if (settingsStr.contains("Veterans Allowed")) {
//			g.setVeteransAllowed(true);
//		}
//		g.setDifficulty(parseDifficulty(settingsStr));
//		m.reset(settingsStr);
//		if (settingsStr.contains("Time Limit")) {
//			m.find();
//			t = LocalTime.parse(m.group(1));
//			d = Duration.between(LocalTime.of(0, 0), t);
//			g.setGameTime(d);
//		}
//		if (settingsStr.contains("Planning Time")) {
//			m.find();
//			t = LocalTime.parse(m.group(1));
//			d = Duration.between(LocalTime.of(0, 0), t);
//			g.setPlanningTime(d);
//		}
	}
	
	
	
//	public void parseTable() {
//		Element gameTable = content.getElementsByTag("tbody").first();
//		Elements rows = gameTable.children();
//		Iterator<Element> it = rows.iterator();
//		Team t = null;
//		List<Team> tl = new ArrayList<>();
//		while(it.hasNext()) {
//			Element row = it.next();
//			if (row.hasClass("team_header")) {
//				t = new Team();
//				tl.add(t);
//			} else {
//				
//				t.addPlayer(null);
//			}
//		}
//	}
	
//	public StatRow parseRow(Element row) {
//		
//		Elements tableData = row.getElementsByTag("td");
//		String place = tableData.get(0).text();
//		String name = tableData.get(1).text();
//		String killed = tableData.get(2).text();
//		String lost = tableData.get(3).text();
//		String damageGiven = tableData.get(5).text();
//		String damageTaken = tableData.get(6).text();
//		String status = tableData.get(8).text();
//		StatRow sr = null;
//		
//		return sr;
//	}
	
	public Difficulty parseDifficulty(String str) {
		Difficulty dif = null;
		if (str.contains("Timid")) {
			dif = new Difficulty(0, "Timid");
		}
		else if (str.contains("Simple")) {
			dif = new Difficulty(1, "Simple");
		}
		else if (str.contains("Heroic")) {
			dif = new Difficulty(3, "Heroic");
		}
		else if (str.contains("Legendary")) {
			dif = new Difficulty(4, "Legendary");
		} else {
			dif = new Difficulty(2, "Normal");
		}
		return dif;
	}

}
