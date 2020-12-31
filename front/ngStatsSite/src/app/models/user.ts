import { Player } from './player';
import { SiteUser } from './site-user';
import { TournamentTeam } from './tournament-team';
export class User {
  id: number;
  tournamentTeams: TournamentTeam[];
  siteUser: SiteUser;
  players: Player[];
  constructor(
    id?: number,
    tournamentTeams?: TournamentTeam[],
    siteUser?: SiteUser,
    players?: Player[],
  ) {
    this.id = id;
    this.tournamentTeams = tournamentTeams;
    this.siteUser = siteUser;
    this.players = players;
  }
}
