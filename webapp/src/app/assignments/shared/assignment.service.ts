import {Student} from "../../students/shared/student.model";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Assignment} from "./assignment.model";
import {catchError} from 'rxjs/operators';
import { Problem } from "../../problems/shared/problem.model";

@Injectable()
export class AssignmentService {
  private assignmentsURL = 'http://localhost:8080/api/assignments';

  constructor(private httpClient: HttpClient) {
  }
 

  saveGrades(studentId: number, problemIdsGrades: Object): Observable<Assignment[]> {
    console.log("problemIdsGrades: ", problemIdsGrades);
    const url = `${this.assignmentsURL}/${studentId}`;
    let assignments = this.createAssignments(studentId, problemIdsGrades);
    console.log("grades: ", assignments);
    console.log("request: ", JSON.stringify({"assignments": assignments}));
    return this.httpClient
      .put<Assignment[]>(url, assignments);
  }

  private createAssignments(studentId: number, problemIdsGrades: Object): Assignment[] {
    const arr: Assignment[] = [];
    Object.keys(problemIdsGrades).forEach(problemId => {
      const sd = new Assignment(
        studentId,
        parseInt(problemId),
        null,
        problemIdsGrades[problemId]);
      arr.push(sd);
    });
    return arr;
  }

  getAssignments(studentId: number): Observable<Assignment[]> {
    return this.httpClient
      .get<Assignment[]>(`${this.assignmentsURL}/${studentId}`); //TODO
  }

  // getAssignment(id: number): Observable<Assignment> {
  //   return this.getAssignments()
  //     .map(assignments => assignments.find(assignment => assignment.id === id));
  // }

  // update(assignment): Observable<Assignment> {
  //   const url = `${this.assignmentsURL}/${assignment.id}`;
  //   return this.httpClient
  //     .put<Assignment>(url, assignment);
  // }

  
  // addAssignment (assignment: Assignment): Observable<Assignment> {
  //   return this.httpClient.post<Assignment>(this.assignmentsURL, assignment);

 
  // }



  // deleteAssignment (assignment: Assignment | number): Observable<Assignment> {
  //   const id = typeof assignment === 'number' ? assignment : assignment.id;
  //   const url = `${this.assignmentsURL}/${id}`;

  //   return this.httpClient.delete<Assignment>(url);
  // }
}
