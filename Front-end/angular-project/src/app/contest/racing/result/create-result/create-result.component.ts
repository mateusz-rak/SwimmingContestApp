import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Result } from 'src/app/entity/Result';
import { Contestant } from 'src/app/entity/Contestant';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-create-result',
  templateUrl: './create-result.component.html',
  styleUrls: ['./create-result.component.css']
})
export class CreateResultComponent implements OnInit {

  constructor(private http: HttpClient, private route: ActivatedRoute) { }

  position: number;
  result: Result = {id: null, position: null, contestant: null};
  idContestant: number;
  contestants: Contestant[];
  json: any;
  id: number;
  idRacing: number;
  readonly url = 'http://localhost:8080/SwimmingContentApplication_war_exploded/rest';

  ngOnInit() {
    this.route.paramMap.subscribe(param => {
      this.id = +param.get('id');
      this.idRacing = +param.get('idRacing');
    });
    this.getAllContestant();
  }


  createResult() {
    this.result.position = this.position;
    // tslint:disable-next-line:max-line-length
    this.http.post(this.url + '/contest/' + this.id + '/racing/' + this.idRacing + '/result/createResult/' + this.idContestant, this.result).toPromise().then((data: any) => {
      console.log(data);
      this.json = JSON.stringify(data.json);
      this.position = null;
      this.idContestant = null;
    });
  }

  getAllContestant() {
    this.http.get(this.url + '/contestant/getAllContestant').subscribe((data: any) => {
      this.contestants = data;
    });
  }

}
