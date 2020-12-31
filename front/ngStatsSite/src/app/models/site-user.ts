import { Tournament } from 'src/app/models/tournament';
import { Tournament } from "./tournament";
import { TournamentTeam } from "./tournament-team";
import { User } from "./user";

export class SiteUser {
  id: number;
  enabled: boolean;
  username: string;
  password: string;
  email: string;
  bio: string;
  profilePicUrl: string;
  role: string;
  metaserverUsers: User[];
  tournaments: Tournament[];
  tournamentTeams: TournamentTeam[];
  creationTimestamp: string;
  updateTimestamp: string;
  constructor(
    id?: number,
    enabled?: boolean,
    username?: string,
    password?: string,
    email?: string,
    bio?: string,
    profilePicUrl?: string,
    role?: string,
    metaserverUsers?: User[],
    tournaments?: Tournament[],
    tournamentTeams?: TournamentTeam[],
    creationTimestamp?: string,
    updateTimestamp?: string,
  ) {
    this.id = id;
    this.enabled = enabled;
    this.username = username;
    this.password = password;
    this.email = email;
    this.bio = bio;
    this.profilePicUrl = profilePicUrl;
    this.role = role;
    this.metaserverUsers = metaserverUsers;
    this.tournaments = tournaments;
    this.tournamentTeams = tournamentTeams;
    this.creationTimestamp = creationTimestamp;
    this.updateTimestamp = updateTimestamp;
  }
}
