import { Component, OnInit } from '@angular/core';
import { Racing } from 'src/app/entity/Racing';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Result } from 'src/app/entity/Result';

@Component({
  selector: 'app-create-racing',
  templateUrl: './create-racing.component.html',
  styleUrls: ['./create-racing.component.css']
})
export class CreateRacingComponent implements OnInit {

  constructor(private http: HttpClient, private route: ActivatedRoute) { }

  distance: number;
  type: string;
  racing: Racing = {id: null, distance : 0 };
  json: any;
  id: number;
  readonly url = 'http://localhost:8080/SwimmingContentApplication_war_exploded/rest';

  ngOnInit() {
    this.route.paramMap.subscribe(param => {
      this.id = +param.get('id');
    });
  }

  createRacing() {
    this.racing.distance = this.distance;
    this.racing.type = this.type;
    this.http.post(this.url + '/contest/' + this.id + '/racing/createRacing', this.racing).toPromise().then((data: any) => {
      console.log(data);
      this.json = JSON.stringify(data.json);
      this.distance = null;
      this.type = '';
    });
  }

}
