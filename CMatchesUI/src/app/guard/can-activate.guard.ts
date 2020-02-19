import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { RouterService } from '../services/router.service';
import { AuthenticationService } from '../services/authentication.service';
import { MatchesService } from '../services/matches.service';
import { stringify } from '@angular/core/src/render3/util';
import { Match } from '../dashboard/match';

@Injectable({
  providedIn: 'root'
})
export class CanActivateGuard implements CanActivate {


  authstatus: Match[];
  iserror: string;

  constructor(
    private routerservice: RouterService,
    private authservice: AuthenticationService,
    private router: Router,
    private matchesservice: MatchesService
  ) {
    this.authstatus = [];
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
   
    const bearertoken = this.authservice.getBearerToken();
    console.log("Can-Activate-Route-guard" + bearertoken);

    if(null==bearertoken){
      console.log("In if block of canActivateGuard" + bearertoken);
      this.routerservice.routeToLogin();
      return false;
    }
    else{
      console.log("In if block of canActivateGuard" + bearertoken);
      return true;
    }

    // this.matchesservice.getAllRecommendedMatchs().subscribe(
    //   (data) => {
    //     console.log("data is :" + data);

    //     console.log("in null block");
    //     this.authstatus = data;

    //   },
    //   (err) => {

    //     this.iserror = err;
    //     this.routerservice.routeToLogin();
    //     return false;
    //   });

    // if (this.authstatus != null) {
    //   return true;
    // }

    // if (this.authstatus == null) {
    //   return false;
    // }
  }
}
