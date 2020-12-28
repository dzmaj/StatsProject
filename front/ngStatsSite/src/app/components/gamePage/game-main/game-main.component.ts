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
  ngOnChanges(): void {
    try {
      let id = Number.parseInt(this.currentRoute.snapshot.paramMap.get('id'));
      this.currentRoute
      if (id) {
        this.gameId = id;
      }
    } catch {
    }
    console.log(this.gameId)
    this.load();
  }
  ngOnDestroy(): void {
    console.log('ngOnDestroy');

  }

  load(): void {
    this.gameService.show(this.gameId).subscribe(
      data => this.game = data,
      err => console.error(err)
    )
  }

}
