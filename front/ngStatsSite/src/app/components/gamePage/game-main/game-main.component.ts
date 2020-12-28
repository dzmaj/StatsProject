import { GameService } from './../../../services/game.service';
import { Component, Input, OnInit } from '@angular/core';
import { Game } from 'src/app/models/game';

@Component({
  selector: 'app-game-main',
  templateUrl: './game-main.component.html',
  styleUrls: ['./game-main.component.css']
})
export class GameMainComponent implements OnInit {

  @Input() gameId: number;
  game: Game;

  constructor(private gameService: GameService) { }

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.gameService.show(this.gameId).subscribe(
      data => this.game = data,
      err => console.error(err)
    )
  }

}
