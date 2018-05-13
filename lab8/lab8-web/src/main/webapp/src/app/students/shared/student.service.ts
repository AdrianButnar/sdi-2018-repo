import {Injectable} from '@angular/core';

import {HttpClient} from '@angular/common/http';

import {Student} from './student.model';

import {Observable} from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class StudentService {
  private studentsUrl = 'http://localhost:8080/api/students';

  constructor(private httpClient: HttpClient) {
  }

  getStudents(): Observable<Student[]> {
    return this.httpClient
      .get<Array<Student>>(this.studentsUrl);
  }

  getStudent(id: number): Observable<Student> {
    return this.getStudents()
      .map(students => students.find(student => student.id === id));
  }

  update(student): Observable<Student> {
    const url = `${this.studentsUrl}/${student.id}`;
    return this.httpClient
      .put<Student>(url, student);
  }

  /** DELETE: delete the student from the server */
  deleteStudent (student: Student | number): Observable<Student> {
    const id = typeof student === 'number' ? student : student.id;
    const url = `${this.studentsUrl}/${id}`;

    return this.httpClient.delete<Student>(url); 
  }

  addStudent (student: Student): Observable<Student> {

    return this.httpClient.post<Student>(this.studentsUrl, student);
  }

}
