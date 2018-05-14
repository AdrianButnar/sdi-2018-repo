import {Student} from "../../students/shared/student.model";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Assignment} from "./assignment.model";
import {catchError} from 'rxjs/operators';

@Injectable()
export class AssignmentService {
  private assignmentsURL = 'http://localhost:8080/api/assignments';

  constructor(private httpClient: HttpClient) {
  }

  getAssignments(): Observable<Assignment[]> {
    return this.httpClient
      .get<Array<Assignment>>(this.assignmentsURL);
  }

  getAssignment(id: number): Observable<Assignment> {
    return this.getAssignments()
      .map(assignments => assignments.find(assignment => assignment.id === id));
  }

  update(assignment): Observable<Assignment> {
    const url = `${this.assignmentsURL}/${assignment.id}`;
    return this.httpClient
      .put<Assignment>(url, assignment);
  }

  addAssignment (assignment: Assignment): Observable<Assignment> {
    return this.httpClient.post<Assignment>(this.assignmentsURL, assignment);
  }

  deleteAssignment (assignment: Assignment | number): Observable<Assignment> {
    const id = typeof assignment === 'number' ? assignment : assignment.id;
    const url = `${this.assignmentsURL}/${id}`;

    return this.httpClient.delete<Assignment>(url);
  }
}
