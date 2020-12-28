import { TournamentService } from './services/tournament.service';
import { GameService } from './services/game.service';
import { AuthService } from './services/auth.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GameMainComponent } from './components/gamePage/game-main/game-main.component';
import { TournamentMainComponent } from './components/tournaments/tournament-main/tournament-main.component';

@NgModule({
  declarations: [
    AppComponent,
    GameMainComponent,
    TournamentMainComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [
    AuthService,
    GameService,
    TournamentService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
