import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Match } from '../dashboard/Match';
import { Observable } from 'rxjs';
import { FavouriteMatch } from '../dashboard/favouriteMatch';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class MatchesService {

  userid: any;

  constructor(private httpClient: HttpClient, private authservice: AuthenticationService) { }

  getAllMatchs(): Observable<Object[]> {
     return this.httpClient.get<Object[]>('http://cricapi.com/api/matches/QJD3pBAefxRJt9Bn92Pew06OTZb2');
  }

/*   getAllOldMatches(): Observable<Object[]> {
     return this.httpClient.get<Object[]>('http://cricapi.com/api/matches/QJD3pBAefxRJt9Bn92Pew06OTZb2');
  }

  getMatchCalendar(): Observable<Object[]> {
    return this.httpClient.get<Object[]>('http://cricapi.com/api/matches/QJD3pBAefxRJt9Bn92Pew06OTZb2');
 }
 
  getAllNewMatches(): Observable<Object[]> {
     return this.httpClient.get<Object[]>('http://cricapi.com/api/matches/QJD3pBAefxRJt9Bn92Pew06OTZb2');
  }

  getAllMatchCalendar(search: string): Observable<Object[]> {
    console.log("Matches Service: " + search);
    // return this.httpClient.get<Object[]>('http://openlibrary.org/search.json?q=search');
     return this.httpClient.get<Object[]>('http://cricapi.com/api/matches/QJD3pBAefxRJt9Bn92Pew06OTZb2');
  } */


  getAllFavouriteMatchs(userid): Observable<Match[]> {
    //Service API call required
    console.log("My Bearer token " + this.authservice.getBearerToken());
    return this.httpClient.get<Match[]>('http://localhost:8765/favouriteservice/api/v1/fetchFavouriteMatches/'+userid, {
      headers: {'Authorization': "Bearer "+this.authservice.getBearerToken() }
    })
  }

  addMatchToFavourite(match: FavouriteMatch) {
console.log(" before server" +match.matchList.length);
    //Add Service API call here
     return this.httpClient.post<FavouriteMatch>('http://localhost:8765/favouriteservice/api/v1/addtoFavourite', match, {
       headers: { 'Access-Control-Allow-Origin': '*', 'Authorization': "Bearer "+this.authservice.getBearerToken() }
     })

    return null;
  }

  getAllRecommendedMatchs(): Observable<Match[]> {
    //Service API call required
    return this.httpClient.get<Match[]>('http://localhost:8765/recommendationservice/api/v1/fetchRecommendedMatches',{
      headers: {'Authorization': "Bearer "+this.authservice.getBearerToken() }
    });
  
    return null;
  }
}