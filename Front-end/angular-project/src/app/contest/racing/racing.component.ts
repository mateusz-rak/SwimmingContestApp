import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Racing } from '../../entity/Racing';
import { HttpClient} from '@angular/common/http';
import { Contest } from 'src/app/entity/Contest';

@Component({
  selector: 'app-racing',
  templateUrl: './racing.component.html',
  styleUrls: ['./racing.component.css']
})
export class RacingComponent implements OnInit {

  constructor(private route: ActivatedRoute, private http: HttpClient) {
   }

  id: number;
  readonly url = 'http://localhost:8080/SwimmingContentApplication_war_exploded/rest';
  racings: Racing[];
  json: any;

  ngOnInit() {
    this.route.paramMap.subscribe(param => {
      this.id = +param.get('id');
    });
    this.getAllRacing();
  }


  getAllRacing() {
    this.http.get(this.url + '/contest/' + this.id + '/getAllRacing' ).subscribe((data: any) => {
      this.racings = data;
    });
  }

  deleteRacing(idR: number) {
    this.http.delete(this.url + '/contest/' + this.id + '/deleteRacing/' + idR).subscribe((data: any) => {
      this.getAllRacing();
    });
  }

}
