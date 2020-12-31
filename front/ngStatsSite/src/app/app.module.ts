import { TournamentService } from './services/tournament.service';
import { GameService } from './services/game.service';
import { AuthService } from './services/auth.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import {MatTabsModule} from '@angular/material/tabs';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatInputModule} from '@angular/material/input';
import {MatTreeModule} from '@angular/material/tree';
import {MatTableModule} from '@angular/material/table';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GameMainComponent } from './components/gamePage/game-main/game-main.component';
import { TournamentMainComponent } from './components/tournaments/tournament-main/tournament-main.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeMainComponent } from './components/home/home-main/home-main.component';
import { NavigatorComponent } from './components/navigator/navigator.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { TournamentTeamComponent } from './components/tournaments/tournament-team/tournament-team.component';
import { TournamentMatchComponent } from './components/tournaments/tournament-match/tournament-match.component';
import { TournamentGameComponent } from './components/tournaments/tournament-game/tournament-game.component';
import { MetaserverUserComponent } from './components/users/metaserver-user/metaserver-user.component';
import { MostRecentNamePipe } from './pipes/most-recent-name.pipe';

@NgModule({
  declarations: [
    AppComponent,
    GameMainComponent,
    TournamentMainComponent,
    HomeMainComponent,
    NavigatorComponent,
    TournamentTeamComponent,
    TournamentMatchComponent,
    TournamentGameComponent,
    MetaserverUserComponent,
    MostRecentNamePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgbModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatCardModule,
    MatTabsModule,
    MatGridListModule,
    MatInputModule,
    MatTreeModule,
    MatTableModule,
  ],
  providers: [
    AuthService,
    GameService,
    TournamentService,
    MostRecentNamePipe,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
