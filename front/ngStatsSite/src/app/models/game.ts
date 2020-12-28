import { TournamentMatch } from './tournament-match';
import { Difficulty } from "./difficulty";
import { Gametype } from "./gametype";
import { Team } from "./team";

export class Game {
  id: number;
  teamCount: number;
  playerCount: number;
  gameName: string;
  mapName: string;
  gametype: Gametype;
  difficulty: Difficulty;
  timeLimit: number;
  planningTimeLimit: number;
  cooperative: boolean;
  allowTeams: boolean;
  allowUnitTrading: boolean;
  allowVeterans: boolean;
  allowAlliances: boolean;
  overheadMap: boolean;
  deathmatch: boolean;
  vTFL: boolean;
  antiClump: boolean;
  endDateTime: string;
  startDateTime: string;
  duration: number;
  recordingURL: string;
  teams: Team[];
  tournamentMatches: TournamentMatch[];

  constructor(
    id: number,
    teamCount: number,
    playerCount: number,
    gameName: string,
    mapName: string,
    gametype: Gametype,
    difficulty: Difficulty,
    timeLimit: number,
    planningTimeLimit: number,
    cooperative: boolean,
    allowTeams: boolean,
    allowUnitTrading: boolean,
    allowVeterans: boolean,
    allowAlliances: boolean,
    overheadMap: boolean,
    deathmatch: boolean,
    vTFL: boolean,
    antiClump: boolean,
    endDateTime: string,
    startDateTime: string,
    duration: number,
    recordingURL: string,
    teams: Team[],
    tournamentMatches: TournamentMatch[],
  ) {
    this.id = id;
    this.teamCount = teamCount;
    this.playerCount = playerCount;
    this.gameName = gameName;
    this.mapName = mapName;
    this.gametype = gametype;
    this.difficulty = difficulty;
    this.timeLimit = timeLimit;
    this.planningTimeLimit = planningTimeLimit;
    this.cooperative = cooperative;
    this.allowTeams = allowTeams;
    this.allowUnitTrading = allowUnitTrading;
    this.allowVeterans = allowVeterans;
    this.allowAlliances = allowAlliances;
    this.overheadMap = overheadMap;
    this.deathmatch = deathmatch;
    this.vTFL = vTFL;
    this.antiClump = antiClump;
    this.endDateTime = endDateTime;
    this.startDateTime = startDateTime;
    this.duration = duration;
    this.recordingURL = recordingURL;
    this.teams = teams;
    this.tournamentMatches = tournamentMatches;
  }
}
