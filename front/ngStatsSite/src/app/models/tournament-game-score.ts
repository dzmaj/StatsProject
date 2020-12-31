import { TournamentGame } from './tournament-game';
import { Team } from './team';
import { TournamentTeam } from './tournament-team';
export class TournamentGameScore {
  id: number;
  score: number;
  tournamentTeam: TournamentTeam;
  team: Team;
  tournamentGame: TournamentGame;
  constructor(
    id?: number,
    score?: number,
    tournamentTeam?: TournamentTeam,
    team?: Team,
    tournamentGame?: TournamentGame,
  ) {
    this.id = id;
    this.score = score;
    this.tournamentTeam = tournamentTeam;
    this.team = team;
    this.tournamentGame = tournamentGame;
  }
}
