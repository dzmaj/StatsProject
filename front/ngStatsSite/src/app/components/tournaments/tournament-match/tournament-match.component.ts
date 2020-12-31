import { TournamentMatch } from './../../../models/tournament-match';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-tournament-match',
  templateUrl: './tournament-match.component.html',
  styleUrls: ['./tournament-match.component.css']
})
export class TournamentMatchComponent implements OnInit {

  @Input() tournamentMatch: TournamentMatch;

  constructor() { }

  ngOnInit(): void {
  }

}
