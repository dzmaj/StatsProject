import { TournamentGameScore } from './tournament-game-score';
import { Game } from 'src/app/models/game';
import { TournamentMatch } from './tournament-match';
export class TournamentGame {
  id: number;
  note: string;
  tournamentMatch: TournamentMatch;
  game: Game;
  tournamentGameScores: TournamentGameScore[];
  constructor(
    id?: number,
    note?: string,
    tournamentMatch?: TournamentMatch,
    game?: Game,
    tournamentGameScores?: TournamentGameScore[],
  ) {
    this.id = id;
    this.note = note;
    this.tournamentMatch = tournamentMatch;
    this.game = game;
    this.tournamentGameScores = tournamentGameScores;
  }
}
