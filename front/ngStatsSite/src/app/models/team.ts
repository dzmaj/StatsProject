import { TournamentGameScore } from './tournament-game-score';
import { Player } from './player';
import { Game } from './game';
export class Team {
  id: number;
  game: Game;
  place: number;
  placeTie: boolean;
  spectators: boolean;
  eliminated: boolean;
  teamName: string;
  players: Player[];
  tournamentGameScores: TournamentGameScore[];
  constructor(
    id?: number,
    game?: Game,
    place?: number,
    placeTie?: boolean,
    spectators?: boolean,
    eliminated?: boolean,
    teamName?: string,
    players?: Player[],
    tournamentGameScores?: TournamentGameScore[],
  ) {
    this.id = id;
    this.game = game;
    this.place = place;
    this.placeTie = placeTie;
    this.spectators = spectators;
    this.eliminated = eliminated;
    this.teamName = teamName;
    this.players = players;
    this.tournamentGameScores = tournamentGameScores;
  }
}
