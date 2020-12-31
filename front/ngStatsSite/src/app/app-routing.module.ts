import { GameMainComponent } from './components/gamePage/game-main/game-main.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeMainComponent } from './components/home/home-main/home-main.component';
import { TournamentMainComponent } from './components/tournaments/tournament-main/tournament-main.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeMainComponent },
  { path: 'games/:id', component: GameMainComponent },
  { path: 'tournaments/:id', component: TournamentMainComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
