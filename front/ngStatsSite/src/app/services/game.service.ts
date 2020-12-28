import { Game } from './../models/game';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private url = environment.baseUrl + 'api/gos/games/'
  private authUrl = environment.baseUrl + 'api/auth/gos/games/'

  constructor(private http: HttpClient, private authService: AuthService) { }

  show(id: number): Observable<Game> {
    const httpOptions = this.authService.getHttpOptions();
    return this.http.get<Game>(this.url + id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error getting game id: ' + id);
      })
    );
  }


}
