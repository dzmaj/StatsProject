import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Tournament } from 'src/app/models/tournament';
import { TournamentService } from 'src/app/services/tournament.service';

@Component({
  selector: 'app-tournament-main',
  templateUrl: './tournament-main.component.html',
  styleUrls: ['./tournament-main.component.css']
})
export class TournamentMainComponent implements OnInit {

  @Input() tournamentId: number;
  tournament: Tournament;

  constructor(private tournamentService: TournamentService, private currentRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.currentRoute.params.subscribe(params => {
      console.log('Loading tournament: '+params['id']);
      try {
        let id = Number.parseInt(this.currentRoute.snapshot.paramMap.get('id'));
        if (id) {
          this.tournamentId = id;
        }
      } catch {
      }
      this.load();
    }
    );
  }


  load(): void {
    this.tournamentService.show(this.tournamentId).subscribe(
      data => this.tournament = data,
      err => console.error(err)
    )
  }

}
