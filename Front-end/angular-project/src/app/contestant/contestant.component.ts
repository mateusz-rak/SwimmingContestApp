import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Contestant } from '../entity/Contestant';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-contestant',
  templateUrl: './contestant.component.html',
  styleUrls: ['./contestant.component.css']
})
export class ContestantComponent implements OnInit {

  constructor(private http: HttpClient) { }

  readonly url = 'http://localhost:8080/SwimmingContentApplication_war_exploded/rest';
  contestants: Observable<Contestant[]>;

  ngOnInit() {
    this.getAllContestant();
  }

  getAllContestant(): Observable<Contestant[]> {
    return this.contestants = this.http.get<Contestant[]>(this.url + '/contestant/getAllContestant');
  }

  deleteContestant(id: number) {
    this.http.delete(this.url + '/contestant/deleteContestant/' + id).subscribe((data: any) => {
      this.getAllContestant();
    });
  }
}
