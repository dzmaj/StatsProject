import { Game } from './game';
import { Tournament } from './tournament';
export class TournamentMatch {
  id: number;
  name: string;
  description: string;
  tournament: Tournament;
  games: Game[];
  constructor(
    id: number,
    name: string,
    description: string,
    tournament: Tournament,
    games: Game[],
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.tournament = tournament;
    this.games = games;
  }
}
