import { User } from './user';
import { Team } from './team';
export class Player {
  id: number;
  team: Team;
  user: User;
  nickName: string;
  teamName: string;
  primaryColor: number;
  secondaryColor: number;
  coatOfArmsIndex: number;
  host: boolean;
  captain: boolean;
  dropped: boolean;
  unitsKilled: number;
  unitsLost: number;
  damageGiven: number;
  damageTaken: number;

  constructor(
    id: number,
    team: Team,
    user: User,
    nickName: string,
    teamName: string,
    primaryColor: number,
    secondaryColor: number,
    coatOfArmsIndex: number,
    host: boolean,
    captain: boolean,
    dropped: boolean,
    unitsKilled: number,
    unitsLost: number,
    damageGiven: number,
    damageTaken: number,
  ) {
    this.id = id;
    this.team = team;
    this.user = user;
    this.nickName = nickName;
    this.teamName = teamName;
    this.primaryColor = primaryColor;
    this.secondaryColor = secondaryColor;
    this.coatOfArmsIndex = coatOfArmsIndex;
    this.host = host;
    this.captain = captain;
    this.dropped = dropped;
    this.unitsKilled = unitsKilled;
    this.unitsLost = unitsLost;
    this.damageGiven = damageGiven;
    this.damageTaken = damageTaken;
  }
}
