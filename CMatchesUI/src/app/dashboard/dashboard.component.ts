import { Component, OnInit } from '@angular/core';
import { MatchesService } from '../services/matches.service';
import { Match } from './match';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FavouriteMatch } from './favouriteMatch';
import { stringify } from 'querystring';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  matchobj: Match;
  matchList: Match[];
  searchkeyword: string;
  searchForm: FormGroup;
  favmatchList: Match[];
  favouriteMatch: FavouriteMatch;
  userid: any;
  favmatchobj: Match;
  arrMatches: string[];
  arrdocList: string[];
  recommendedList: Match[];
  matchobj1: Match;
  text ='';
  submitMessage: string;
  constructor(private matchesService: MatchesService, private fb: FormBuilder,private authservice:AuthenticationService) {
    this.matchobj = new Match();
    this.matchList = [];
    this.favmatchList = [];
    this.recommendedList = [];
    this.favouriteMatch = new FavouriteMatch();
    this.favouriteMatch.matchList = [];
    this.arrMatches = [] ;
    this.searchForm = fb.group({
    
      searchkeyword: ['', Validators.required]

    })
  }

  ngOnInit() {
    this.arrMatches = [] ;
     this.authservice.share.subscribe((data=>{
      this.userid =data;
       console.log("share data is: "+this.userid);
     })
      );
    console.log("UserId value is:" + this.userid);

    if(null!= this.authservice.getBearerToken()){
      console.log("In Favorite call "+this.authservice.getBearerToken());
      this.userid =this.authservice.getUserToken();
    this.matchesService.getAllFavouriteMatchs(this.userid).subscribe(
      data => {
        if (null != data) {
          this.favmatchList = data;
        }

        else{
          this.favmatchList =[];
        }
        console.log(" Fav data"+data[0]);
      }
      ,
      err => console.log("Dashboard Error:"+err)
    )
  } 
     //Fetching recommended Matchs
    
    if(null!= this.authservice.getBearerToken()){
      console.log("In Dashboard recommended service call");
     this.matchesService.getAllRecommendedMatchs().subscribe(
       data => {
         //console.log(" Fav data"+data[0]);
         if(null!=data){
         this.recommendedList = data;
         }
       }
       ,
       err => console.log(err)
     )
 
    //console.log(this.matchList);
  }
}

  searchMatch(searchForm) {
    this.searchkeyword = this.searchForm.value['searchkeyword'];

    if (this.searchkeyword != null && this.searchkeyword.length >0 ) {
      this.submitMessage ="";
      this.matchesService.getAllMatchs().subscribe(
        data => {
          this.arrMatches = data["matches"];
          console.log("Data received:" +this.arrMatches);
          for (var i = 0; i < this.arrMatches.length; i++) {
            this.matchobj = new Match();
            this.matchobj.unique_ID = this.arrMatches[i]["unique_id"];
            this.matchobj.team1 = this.arrMatches[i]["team-1"];
            this.matchobj.team2 = this.arrMatches[i]["team-2"];
            this.matchobj.squad = this.arrMatches[i]["squad"];
            this.matchobj.date = this.arrMatches[i]["date"];
            this.matchobj.matchStarted = this.arrMatches[i]["matchStarted"];
            this.matchList.push(this.matchobj);
          }
          console.log("Json" + this.arrMatches.length);
          console.log("Match list is :" + this.matchList);

        }
        ,
        err => console.log(err)
      )

    }

    else{
      this.submitMessage = "Enter search String";
      console.log("In Else block:"+this.submitMessage );
    }
  }


  addToFavourite(match: Match) {
    console.log("coveri" + "   " + match.unique_ID);

    this.matchobj1 = new Match();
    this.matchobj1.unique_ID = match.unique_ID;
    this.matchobj.team1 = match.team1;
    this.matchobj.team2 = match.team2;
    this.matchobj.squad = match.squad;
    this.matchobj.date = match.date;
    this.matchobj.matchStarted = match.matchStarted;

    this.favmatchList.push(match);
    this.favouriteMatch.matchList = this.favmatchList;
    this.favouriteMatch.userId = this.userid;
    console.log("Add favourite service call: fav matches" + this.favouriteMatch.matchList.length);

    this.matchesService.addMatchToFavourite(this.favouriteMatch).subscribe(
      data => {

      },
      err => { }
    )
    this.matchobj = new Match();
  }

}
