import { TournamentTeam } from './tournament-team';
import { TournamentGame } from './tournament-game';
import { Tournament } from './tournament';
export class TournamentMatch {
  id: number;
  name: string;
  description: string;
  tournament: Tournament;
  tournamentGames: TournamentGame[];
  tournamentTeams: TournamentTeam[];
  updateTimestamp: string;
  scheduledTime: string;
  constructor(
    id?: number,
    name?: string,
    description?: string,
    tournament?: Tournament,
    tournamentGames?: TournamentGame[],
    tournamentTeams?: TournamentTeam[],
    updateTimestamp?: string,
    scheduledTime?: string,

  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.tournament = tournament;
    this.tournamentGames = tournamentGames;
    this.tournamentTeams = tournamentTeams;
    this.updateTimestamp = updateTimestamp;
    this.scheduledTime = scheduledTime;
  }
}
