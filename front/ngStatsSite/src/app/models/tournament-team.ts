import { Team } from './team';
import { Tournament } from './tournament';
import { User } from './user';
export class TournamentTeam {
  id: number;
  name: string;
  description: string;
  tournament: Tournament;
  gameTeams: Team[];
  metaserverUsers: User[];
  constructor(
    id: number,
    name: string,
    description: string,
    tournament: Tournament,
    gameTeams: Team[],
    metaServerUsers: User[],
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.tournament = tournament;
    this.gameTeams = gameTeams;
    this.metaserverUsers = metaServerUsers;
  }
}
