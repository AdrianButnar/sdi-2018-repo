import {Student} from "../../students/shared/student.model";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Problem} from "./problem.model";

@Injectable()
export class ProblemService {
  private problemsURL = 'http://localhost:8080/api/problems';

  constructor(private httpClient: HttpClient) {
  }

  getProblems(): Observable<Problem[]> {
    return this.httpClient
      .get<Array<Problem>>(this.problemsURL);
  }

  getProblem(id: number): Observable<Problem> {
    return this.getProblems()
      .map(problems => problems.find(problem => problem.id === id));
  }

  update(problem): Observable<Problem> {
    const url = `${this.problemsURL}/${problem.id}`;
    return this.httpClient
      .put<Problem>(url, problem);
  }
}
