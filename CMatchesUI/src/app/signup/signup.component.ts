import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { User } from '../login/user';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  userobj:User;
  userobjregister:User;

  constructor(private authservice:AuthenticationService,private router:RouterService) {
    this.userobj =new User();
    
   }

  ngOnInit() {
  }

  signUpUser(signUpForm:NgForm)
  {
    this.userobj = signUpForm.value;
    console.log("this.userobj"+this.userobj['upass']);

  this.userobjregister =new User();
  this.userobjregister.userid =this.userobj['userid'];
  this.userobjregister.userPassword =this.userobj['upass'];
  this.userobjregister.userRole="";
  this.userobjregister.firstName=this.userobj['ufirstname'];
  this.userobjregister.lastName =this.userobj['ulastname'];
  this.userobjregister.userAddedDate = new Date();
 
   
    console.log( "this.userobjregister "+this.userobjregister);

    this.authservice.registerUser(this.userobjregister).subscribe(
      res => {
        
        console.log("Registration Successful");
        this.router.routeToLogin();
      
      },
      err => {
        if (err.status === 403) {
          console.log(err.error.message);
        }
        else {
          console.log(err.message);
        }
      })
  }

}
