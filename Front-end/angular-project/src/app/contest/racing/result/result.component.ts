import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Result } from 'src/app/entity/Result';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  idContest: number;
  idRacing: number;
  readonly url = 'http://localhost:8080/SwimmingContentApplication_war_exploded/rest';
  results: Result[];
  json: any;

  ngOnInit() {
    this.route.paramMap.subscribe(param => {
      this.idContest = +param.get('id');
      this.idRacing = +param.get('idRacing');
    });
    this.getAllResult();
  }

  getAllResult() {
    this.http.get(this.url + '/contest/' + this.idContest + '/racing/' + this.idRacing + '/getAllResult' ).subscribe((data: any) => {
      this.results = data;
    });
  }

  deleteResult(idR: number) {
    this.http.delete(this.url + '/contest/' + this.idContest + '/racing/' + this.idRacing + '/deleteResult/' + idR)
    .subscribe((data: any) => {
      this.getAllResult();
    });
  }

}
