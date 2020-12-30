import { Team } from './../../../models/team';
import { GameService } from './../../../services/game.service';
import { Component, Input, OnInit } from '@angular/core';
import { Game } from 'src/app/models/game';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-game-main',
  templateUrl: './game-main.component.html',
  styleUrls: ['./game-main.component.css']
})
export class GameMainComponent implements OnInit {

  @Input() gameId: number;
  game: Game;

  constructor(private gameService: GameService, private currentRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.currentRoute.params.subscribe(params => {
      console.log('Loading game: '+params['id']);
      try {
        let id = Number.parseInt(this.currentRoute.snapshot.paramMap.get('id'));
        if (id) {
          this.gameId = id;
        }
      } catch {
      }
      this.load();
    }
    );
    this.load();
  }


  load(): void {
    this.gameService.show(this.gameId).subscribe(
      data => this.game = data,
      err => console.error(err)
    )
  }

  calcTeamStatsTotal(team: Team, stat: string): number {
    let total = 0;
    team.players.forEach(player => {
      total += player[stat];
    });
    return total;
  }

}
