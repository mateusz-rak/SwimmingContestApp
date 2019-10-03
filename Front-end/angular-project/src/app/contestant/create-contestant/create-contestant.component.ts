import { Component, OnInit } from '@angular/core';
import { Contestant } from 'src/app/entity/Contestant';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-create-contestant',
  templateUrl: './create-contestant.component.html',
  styleUrls: ['./create-contestant.component.css']
})
export class CreateContestantComponent implements OnInit {

  constructor(private http: HttpClient) { }

  contestant: Contestant = {name : null, surname : null , sex: null, age : null, id : null};
  readonly url = 'http://localhost:8080/SwimmingContentApplication_war_exploded/rest';
  name = '';
  surname = '';
  sex = '';
  age: number = null;
  json;

  ngOnInit() {
  }

  createContestant() {
    this.contestant.name = this.name;
    this.contestant.surname = this.surname;
    this.contestant.sex = this.sex;
    this.contestant.age = this.age;
    this.http.post<Contestant>(this.url + '/contestant/createContestant', this.contestant).subscribe((data: any) => {
      this.clear();
      console.log(data);
      this.json = JSON.stringify(data.json);
    });
  }

  clear() {
    this.age = null;
    this.name = '';
    this.surname = '';
    this.sex = '';
  }

}
