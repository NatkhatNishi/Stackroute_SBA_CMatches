import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { Subscription } from 'rxjs';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit,OnDestroy {
  title = 'CMatchesUI';
  userIsAuthenticated = false;
  name:string="";
  private authListenerSubs: Subscription;
  constructor(private authService: AuthenticationService,private routerservice : RouterService) { }

  ngOnInit() {
    console.log('authticate before::'+this.userIsAuthenticated);
    this.userIsAuthenticated = this.authService.getIsAuth();
    // this.authListenerSubs = this.authService.getAuthStatusListener().subscribe(isAuthenticated => {
    //   this.userIsAuthenticated = isAuthenticated;
    //   console.log('authticate::'+this.userIsAuthenticated);
    //   if (this.userIsAuthenticated) {
    //     this.name = this.authService.getName() ? this.authService.getName() : 'Welcome!';
    //     if(this.name=='Welcome!'){
    //       this.userIsAuthenticated=false;
    //     }
    //     console.log('name::'+this.name);
    //   }
     
    // });
  }
  switchToLogin(){
    console.log('Enter into Login');
   this.routerservice. routeToLogin();

  }

  switchToRegister()
  {
    console.log('Enter into Register');
   // this.routeservice.routeToRecommendView();
  }

  onLogout() {
    this.authService.logout();
  }
  ngOnDestroy() {
    this.authListenerSubs.unsubscribe();
  }
 
}
