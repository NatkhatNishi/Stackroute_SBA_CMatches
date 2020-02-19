import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormBuilder, FormGroup } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { RouterService } from '../services/router.service';
import { User } from '../login/user';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userobj: User;
  bearerToken: string;
  submitMessage: string;
  user: User;
  loginForm: FormGroup;
  text = '';
  username: string;
  userid:string;

  constructor(private routerservice: RouterService, private fb: FormBuilder, private authservice: AuthenticationService) {
    this.userobj = new User();
    this.loginForm = fb.group({

      username: ['', Validators.required],
      password: ['', Validators.required]

    })
  }
  ngOnInit() {
  }

  loginUser(loginForm) {

    this.userobj = loginForm.value;
    this.user = new User();
    this.user.userid = this.userobj['username'];
    this.user.userPassword = this.userobj['password'];
    this.user.firstName = "";
    this.user.lastName = "";
    this.user.userRole = "";

    console.log("userid "+this.userobj['username']);

    this.authservice.updateData(this.user.userid);

    this.authservice.authenticateUser(this.user).subscribe(
      res => {
        this.bearerToken = res['token'];
        console.log(this.bearerToken);
        this.authservice.setBearerToken(this.bearerToken);
        this.authservice.setUserToken(this.user.userid);

        this.username = this.user.userid;
        console.log("Welcome token stored");
        this.routerservice.routeToDashboard();
      },
      err => {
        if (err.status =!null) {
          this.submitMessage = "Invalid userid/Password";
          console.log(this.submitMessage);
        }
        else {
          this.submitMessage = "Invalid userid/Password";
          console.log(err.message);
        }
      })
  }
}
