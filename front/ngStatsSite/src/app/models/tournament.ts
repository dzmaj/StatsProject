import { SiteUser } from './site-user';
import { TournamentMatch } from "./tournament-match";
import { TournamentTeam } from "./tournament-team";

export class Tournament {
  id: number;
  name: string;
  description: string;
  tournamentTeams: TournamentTeam[];
  tournamentMatches: TournamentMatch[];
  owner: SiteUser;
  constructor(
    id?: number,
    name?: string,
    description?: string,
    tournamentTeams?: TournamentTeam[],
    tournamentMatches?: TournamentMatch[],
    owner?: SiteUser,
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.tournamentTeams = tournamentTeams;
    this.tournamentMatches = tournamentMatches;
    this.owner = owner;
  }
}
