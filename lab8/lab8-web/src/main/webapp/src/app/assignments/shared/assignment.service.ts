import {Student} from "../../students/shared/student.model";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Assignment} from "./assignment.model";

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
}
