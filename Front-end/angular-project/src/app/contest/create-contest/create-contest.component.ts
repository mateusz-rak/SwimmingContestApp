import { Component, OnInit } from '@angular/core';
import { Contest } from 'src/app/entity/Contest';
import { Racing } from 'src/app/entity/Racing';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-create-contest',
  templateUrl: './create-contest.component.html',
  styleUrls: ['./create-contest.component.css']
})
export class CreateContestComponent implements OnInit {

  constructor(private http: HttpClient) { }

  location = '';
  readonly url = 'http://localhost:8080/SwimmingContentApplication_war_exploded/rest';
  contest: Contest = {idContest: null};
  date: string;
  json: any;

  ngOnInit() {
  }


  createContest() {
    this.contest.location = this.location;
    this.contest.date = this.date;
    this.contest.racings = new Array<Racing>();
    this.http.post(this.url + '/contest/createContest', this.contest).toPromise().then((data: any) => {
      console.log(data);
      this.json = JSON.stringify(data.json);
      this.location = '';
      this.date = null;
    });
  }

}
