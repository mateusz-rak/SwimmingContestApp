import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Contestant } from 'src/app/entity/Contestant';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-contestant',
  templateUrl: './update-contestant.component.html',
  styleUrls: ['./update-contestant.component.css']
})
export class UpdateContestantComponent implements OnInit {


  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  contestant: Contestant = {name : null, surname : null , sex: null, age : null, id : null};
  readonly url = 'http://localhost:8080/SwimmingContentApplication_war_exploded/rest';
  id: number;
  name = '';
  surname = '';
  sex = '';
  age: number = null;
  json;

  ngOnInit() {
    this.route.paramMap.subscribe(param => {
      this.id = +param.get('id');
    });
    this.getContestantById();
  }

  getContestantById() {
    this.http.get(this.url + '/contestant/' + this.id).subscribe((data: any) => {
      this.contestant = data;
      this.name = this.contestant.name;
      this.surname = this.contestant.surname;
      this.sex = this.contestant.sex;
      this.age = this.contestant.age;
    });
  }

  updateContestant() {
    this.contestant.name = this.name;
    this.contestant.surname = this.surname;
    this.contestant.sex = this.sex;
    this.contestant.age = this.age;
    this.http.put(this.url + '/contestant/updateContestant/' + this.contestant.id, this.contestant).subscribe((data: any) => {
      console.log(data);
      this.json = JSON.stringify(data.json);
    });
  }

}
