import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

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
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
