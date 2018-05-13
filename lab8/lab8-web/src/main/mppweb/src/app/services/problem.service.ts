import { PROBLEMS } from '../mock-problems';
import { Problem } from '../problem';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs/observable/of';

@Injectable() //{providedIn: 'root',} this can be one error
export class ProblemService {

  constructor() { }

  getStudents(): Observable<Problem[]> {
    // TODO: send the message _after_ fetching the heroes
    //this.messageService.add('HeroService: fetched heroes');
    return of(PROBLEMS);
  }
}
