import { SiteUser } from './site-user';
import { TournamentMatch } from './tournament-match';
import { Tournament } from './tournament';
import { TournamentGameScore } from './tournament-game-score';
import { User } from './user';
export class TournamentTeam {
  id: number;
  name: string;
  description: string;
  tournament: Tournament;
  tournamentGameScores: TournamentGameScore[];
  metaserverUsers: User[];
  tournamentMatches: TournamentMatch[];
  creationTimestamp: string;
  updateTimestamp: string;
  owner: SiteUser;
  constructor(
    id?: number,
    name?: string,
    description?: string,
    tournament?: Tournament,
    tournamentGameScores?: TournamentGameScore[],
    metaServerUsers?: User[],
    tournamentMatches?: TournamentMatch[],
    creationTimestamp?: string,
    updateTimestamp?: string,
    owner?: SiteUser,
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.tournament = tournament;
    this.tournamentGameScores = tournamentGameScores;
    this.metaserverUsers = metaServerUsers;
    this.tournamentMatches = tournamentMatches;
    this.creationTimestamp = creationTimestamp;
    this.updateTimestamp = updateTimestamp;
    this.owner = owner;
  }
}
