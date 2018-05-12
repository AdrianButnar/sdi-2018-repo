import { STUDENTS } from '../mock-students';
import { Student } from '../student';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable() //{providedIn: 'root',} this can be one error
export class StudentService {

  constructor() { }

  getStudents(): Observable<Student[]> {
    // TODO: send the message _after_ fetching the heroes
    //this.messageService.add('HeroService: fetched heroes');
    return of(STUDENTS);
  }
}
