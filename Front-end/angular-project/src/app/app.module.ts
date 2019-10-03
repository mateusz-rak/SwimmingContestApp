import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { ContestantComponent } from './contestant/contestant.component';
import { ContestComponent } from './contest/contest.component';
import { RacingComponent } from './contest/racing/racing.component';
import { CreateContestComponent } from './contest/create-contest/create-contest.component';
import { CreateContestantComponent } from './contestant/create-contestant/create-contestant.component';
import { CreateRacingComponent } from './contest/racing/create-racing/create-racing.component';
import { ResultComponent } from './contest/racing/result/result.component';
import { CreateResultComponent } from './contest/racing/result/create-result/create-result.component';
import { UpdateContestantComponent } from './contestant/update-contestant/update-contestant.component';

@NgModule({
  declarations: [
    AppComponent,
    ContestantComponent,
    ContestComponent,
    RacingComponent,
    CreateContestComponent,
    CreateContestantComponent,
    CreateRacingComponent,
    ResultComponent,
    CreateResultComponent,
    UpdateContestantComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BsDatepickerModule.forRoot(),
    BrowserAnimationsModule,
    RouterModule.forRoot([
      {
        path: '',
        component: ContestantComponent
      },
      {
        path: 'contest',
        component: ContestComponent
      },
      {
        path: 'contestant',
        component: ContestantComponent
      },
      {
        path: 'contest/:id/racing',
        component: RacingComponent
      },
      {
        path: 'contestant/createContestant',
        component: CreateContestantComponent
      },
      {
        path: 'contestant/:id',
        component: UpdateContestantComponent
      },
      {
        path: 'contest/createContest',
        component: CreateContestComponent
      },
      {
        path: 'contest/:id/racing/createRacing',
        component: CreateRacingComponent
      },
      {
        path: 'contest/:id/racing/:idRacing/result',
        component: ResultComponent
      },
      {
        path: 'contest/:id/racing/:idRacing/result/createResult',
        component: CreateResultComponent
      }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
