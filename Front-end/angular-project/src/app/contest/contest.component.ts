import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Racing } from '../entity/Racing';
import { Observable } from 'rxjs';
import { Contest } from '../entity/Contest';
import * as moment from 'moment';



@Component({
  selector: 'app-contest',
  templateUrl: './contest.component.html',
  styleUrls: ['./contest.component.css']
})
export class ContestComponent implements OnInit {

  constructor(private http: HttpClient) {
  }



  readonly url = 'http://localhost:8080/SwimmingContentApplication_war_exploded/rest';
  contests: Contest[];

  ngOnInit() {
    this.getAllContest();
  }

  deleteContest(id: number) {
    this.http.delete(this.url + '/contest/deleteContest/' + id).subscribe((data: any) => {
      this.getAllContest();
    });
  }

  getAllContest() {
    this.http.get(this.url + '/contest/getAllContest').subscribe((data: any) => {
      this.contests = data;
    });
  }



}
