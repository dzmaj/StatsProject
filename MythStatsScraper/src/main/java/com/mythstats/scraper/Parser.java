package com.mythstats.scraper;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mythstats.data.entities.Difficulty;
import com.mythstats.data.entities.Game;
import com.mythstats.data.entities.Gametype;
import com.mythstats.data.entities.Player;
import com.mythstats.data.entities.Team;
import com.mythstats.data.entities.User;

public class Parser {

	private static final String URL_BASE = "http://gateofstorms.net/games/";
	private Element content;
	private Game game;
	private String gameHostNameStr;
	private Connection conn;

	public Game parse(int gameId) {
		game = null;
		String url = URL_BASE + gameId;
		Document doc;
		try {
			conn = Jsoup.connect(url);
			if (conn.response().statusCode() == 404) {
				return null;
			}
			doc = conn.get();
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
		parseTable();
		game.setTeamCount(game.getTeams().size());
		int playerCount = 0;
		for (Team team : game.getTeams()) {
			playerCount += team.getPlayers().size();
			team.getPlayers().get(0).setCaptain(true);
		}
		game.setPlayerCount(playerCount);

		// TODO: gameName
		// TODO: cooperative (edge case where game is custom gametype but not coop)
		// TODO: recordingURL

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
		} else if (tempStr.contains("Balls on Parade")) {
			gameType = "Balls on Parade";
			gameMesh = tempStr.substring(19);
		} else {
			strArr = tempStr.split(" on ", 2);
			gameType = strArr[0];
			gameMesh = strArr[1];
		}

		tempStr = metaPart1.selectFirst("p").html().replaceAll("<p>", "");
		strArr = tempStr.split("<br> Began at ");
		gameHostNameStr = strArr[0].replaceAll("Hosted by ", "");
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
		game.setDuration((int) d.getSeconds() * 30);
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
		String regex = "(\\d{2}:\\d{2}:\\d{2})";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(settingsStr);
		if (settingsStr.contains("Unit Trading Enabled")) {
			game.setAllowUnitTrading(true);
		}
		if (!settingsStr.contains("No Alliances")) {
			game.setAllowAlliances(true);
		}
		if (settingsStr.contains("Teams Allowed")) {
			game.setAllowTeams(true);
		}
		if (settingsStr.contains("Veterans Allowed")) {
			game.setAllowVeterans(true);
		}
		if (settingsStr.contains("Overhead")) {
			game.setOverheadMap(false);
		} else {
			game.setOverheadMap(true);
		}
		if (settingsStr.contains("vTFL")) {
			game.setvTFL(true);
		}
		if (settingsStr.contains("Clump")) {
			game.setAntiClump(true);
		}
		if (settingsStr.contains("Deathmatch")) {
			game.setDeathmatch(true);
		}
		game.setDifficulty(parseDifficulty(settingsStr));
		m.reset(settingsStr);
		if (settingsStr.contains("Time Limit")) {
			m.find();
			t = LocalTime.parse(m.group(1));
			d = Duration.between(LocalTime.of(0, 0), t);
			game.setTimeLimit((int) d.getSeconds() * 30);
		} else {
			game.setTimeLimit(-1);
		}
		if (settingsStr.contains("Planning Time")) {
			m.find();
			t = LocalTime.parse(m.group(1));
			d = Duration.between(LocalTime.of(0, 0), t);
			game.setPlanningTimeLimit((int) d.getSeconds() * 30);
		} else {
			game.setPlanningTimeLimit(0);
		}

	}

	private void parseTable() {
		Element gameTable = content.getElementsByTag("tbody").first();
		Elements rows = gameTable.children();
		Iterator<Element> it = rows.iterator();
		Team t = null;
		Player p = null;
		while (it.hasNext()) {
			Element row = it.next();
			Element siblingRow = row.nextElementSibling();
			if (row.html().contains("<td>Spectators</td>")) {
				t = parseSpectatorTeamRow(row);
				game.addTeam(t);
			}

			// is team or not
			else if (row.hasClass("team_header")) {
				t = parseTeamRow(row);
				// if no sibling and this is team, then this is also player
				if (siblingRow == null) {
					p = parsePlayerRow(row);
					if (p != null) {
						t.addPlayer(p);
					}
				}
				// if sibling is team and this is team, then this is also player
				else if (siblingRow.hasClass("team_header")) {
					p = parsePlayerRow(row);
					if (p != null) {
						t.addPlayer(p);
					}
				}
				// if sibling is spectator row and this is team, then this is also player
				else if (siblingRow.html().contains("<td>Spectators</td>")) {
					p = parsePlayerRow(row);
					if (p != null) {
						t.addPlayer(p);
					}
				}
				game.addTeam(t);
			} else {
				if (!t.isSpectators()) {
					p = parsePlayerRow(row);
					if (p != null) {
						t.addPlayer(p);
					}
				} else {
					p = parseSpectatorPlayerRow(row);
					if (p != null) {
						t.addPlayer(p);
					}
				}
			}

		}
	}

	private Player parseSpectatorPlayerRow(Element row) {
		Player player = null;
		
		try {
			player = new Player();
			Elements tableData = row.getElementsByTag("td");

			String name = tableData.get(1).text();
			player.setNickName(name);
			String userId = null;
			try {
				userId = tableData.get(1).child(0).attr("href");
				userId = userId.substring(7, userId.length() - 1);
				User user = new User();
				user.setId(Integer.parseInt(userId));
				player.setUser(user);
			} catch (Exception e) {
				userId = "-1";
			}
			if (player.getNickName().equals(gameHostNameStr)) {
				player.setHost(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return player;
	}

	private Player parsePlayerRow(Element row) {
		Player player = null;

		try {
			player = new Player();

			Elements tableData = row.getElementsByTag("td");
			String place = tableData.get(0).text();

			String name = tableData.get(1).text();
			player.setNickName(name);
			String userId = null;
			try {
				userId = tableData.get(1).child(0).attr("href");
				userId = userId.substring(7, userId.length() - 1);
				User user = new User();
				user.setId(Integer.parseInt(userId));
				player.setUser(user);
			} catch (Exception e) {
				userId = "-1";
			}
			String killed = tableData.get(2).text();
			player.setUnitsKilled(Integer.parseInt(killed));
			String lost = tableData.get(3).text();
			player.setUnitsLost(Integer.parseInt(lost));
			String damageGiven = tableData.get(5).text();
			player.setDamageGiven(Integer.parseInt(damageGiven));
			String damageTaken = tableData.get(6).text();
			player.setDamageTaken(Integer.parseInt(damageTaken));

			String status = tableData.get(8).text();
			if (status.equals("Dropped")) {
				player.setDropped(true);
			}
			if (player.getNickName().equals(gameHostNameStr)) {
				player.setHost(true);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return player;
	}

	private Team parseSpectatorTeamRow(Element row) {
		Team team = new Team();
		team.setSpectators(true);
		team.setPlace(-1);
		return team;
	}

	private Team parseTeamRow(Element row) {
		Elements tableData = row.getElementsByTag("td");
		String place = tableData.get(0).text();

		String status = tableData.get(8).text();
		String name = tableData.get(1).text();
//		System.out.println(tableData);
//		System.out.println(place);

		Team team = new Team();
		team.setTeamName(name);
		team.setPlace(Integer.parseInt(place));
		if (status.equals("Eliminated")) {
			team.setEliminated(true);
		}
		return team;
	}

	private Difficulty parseDifficulty(String str) {
		Difficulty dif = null;
		if (str.contains("Timid")) {
			dif = new Difficulty(0, "Timid");
		} else if (str.contains("Simple")) {
			dif = new Difficulty(1, "Simple");
		} else if (str.contains("Heroic")) {
			dif = new Difficulty(3, "Heroic");
		} else if (str.contains("Legendary")) {
			dif = new Difficulty(4, "Legendary");
		} else {
			dif = new Difficulty(2, "Normal");
		}
		return dif;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
