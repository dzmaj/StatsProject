import { TournamentMatch } from "./tournament-match";
import { TournamentTeam } from "./tournament-team";

export class Tournament {
  id: number;
  name: string;
  description: string;
  tournamentTeams: TournamentTeam[];
  tournamentMatches: TournamentMatch[];
  constructor(
    id: number,
    name: string,
    description: string,
    tournamentTeams: TournamentTeam[],
    tournamentMatches: TournamentMatch[],
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.tournamentTeams = tournamentTeams;
    this.tournamentMatches = tournamentMatches;
  }
}
