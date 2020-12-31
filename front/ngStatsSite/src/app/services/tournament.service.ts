import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Tournament } from '../models/tournament';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TournamentService {

  private url = environment.baseUrl + 'api/tournaments/'
  private authUrl = environment.baseUrl + 'api/auth/tournaments/'

  constructor(private http: HttpClient, private authService: AuthService) { }

  show(id: number): Observable<Tournament> {
    const httpOptions = this.authService.getHttpOptions();
    return this.http.get<Tournament>(this.url + id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error getting tournament id: ' + id);
      })
    );
  }


}
