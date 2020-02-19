import { Injectable, EventEmitter } from '@angular/core';
import { promise } from 'protractor';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { User } from '../login/user';
import { BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private content = new BehaviorSubject<String>("Default-data");
  public share = this.content.asObservable();

  constructor(private httpClient: HttpClient,private router: Router) {

  }

  updateData(text) {
    this.content.next(text);

  }

  authenticateUser(user: User) {
    console.log("In Auth" + user["userName"]);
    console.log("In Auth" + user);
    return this.httpClient.post<User>('http://localhost:8765/user-auth/api/v1/auth/login', user, { headers: {'Access-Control-Allow-Origin': '*'}});
  }

  setBearerToken(token) {
    localStorage.setItem('keeptoken', token);
  }

  getBearerToken() {
    return localStorage.getItem('keeptoken');
  }

  setUserToken(token) {
    localStorage.setItem('keeptokenuserid', token);
  }

  getUserToken() {
    return localStorage.getItem('keeptokenuserid');
  }

  registerUser(user: User) {

    console.log("In RegisterUser" + user['userId']);
    console.log("In RegisterUser" + user);

    return this.httpClient.post<User>('http://localhost:8765/user-auth/api/v1/auth/register', user , { headers: {'Access-Control-Allow-Origin': '*'}});

  }
  getIsAuth() {
    return this.getBearerToken()!=null? true : false;
  }
  logout() {
    sessionStorage.clear();
    this.router.navigate(['/login']);
}
}
