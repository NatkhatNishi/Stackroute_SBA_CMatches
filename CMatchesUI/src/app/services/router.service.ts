import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class RouterService {

  constructor(private router: Router, private location: Location) { }

  routeToDashboard() {
    console.log("Inside routeToDashboard: ");
    this.router.navigate(['dashboard']);
  }

  routeToLogin() {
    console.log("Inside routeToLogin: ");
    this.router.navigate(['login']);
  }

  routeBack() {
    console.log("Inside routeBack: ");
    this.location.back();
  }

}
