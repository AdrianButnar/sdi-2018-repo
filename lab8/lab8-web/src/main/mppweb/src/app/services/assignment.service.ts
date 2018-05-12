import { Assignment } from './../assignment';
import { ASSIGNMENTS } from './../mock-assignment';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable()//this can be one error

export class AssignmentService {

  constructor() { }
  
  getAssignments(): Observable<Assignment[]> {
    // TODO: send the message _after_ fetching the heroes
    // this.messageService.add('HeroService: fetched heroes');
    return of(ASSIGNMENTS);
  }
}
