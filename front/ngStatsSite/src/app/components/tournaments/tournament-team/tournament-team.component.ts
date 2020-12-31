import { TournamentTeam } from './../../../models/tournament-team';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-tournament-team',
  templateUrl: './tournament-team.component.html',
  styleUrls: ['./tournament-team.component.css']
})
export class TournamentTeamComponent implements OnInit {

  @Input() tournamentTeam: TournamentTeam;
  editTeam: TournamentTeam;

  constructor() { }

  ngOnInit(): void {
  }

}
