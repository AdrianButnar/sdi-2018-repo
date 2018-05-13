import { Assignment } from './../assignment';
import { ASSIGNMENTS } from './../mock-assignment';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs/observable/of';

@Injectable()//this can be one error

export class AssignmentService {

  constructor() { }
  
  getAssignments(): Observable<Assignment[]> {
    // TODO: send the message _after_ fetching the heroes
    // this.messageService.add('HeroService: fetched heroes');
    return of(ASSIGNMENTS);
  }
}
