import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {StudentDetailComponent} from "./students/student-detail/student-detail.component";
import {StudentsComponent} from "./students/students.component";
import {StudentListComponent} from "./students/student-list/student-list.component";
import {StudentService} from "./students/shared/student.service";
import {ProblemsComponent} from "./problems/problems.component";
import {ProblemListComponent} from "./problems/problems-list/problem-list.component";
import {ProblemDetailComponent} from "./problems/problem-detail/problem-detail.component";
import {ProblemService} from "./problems/shared/problem.service";
//aici vine partea de assignments(in caz ca crapa, dar speram ca nu)
import {AssignmentsComponent} from "./assignments/assignments.component";
import {AssignmentListComponent} from "./assignments/assignments-list/assignment-list.component";
import {AssignmentDetailComponent} from "./assignments/assignment-detail/assignment-detail.component";
import {AssignmentService} from "./assignments/shared/assignment.service";


@NgModule({
  declarations: [
    AppComponent,

    StudentDetailComponent,
    StudentsComponent,
    StudentListComponent,

    ProblemDetailComponent,
    ProblemsComponent,
    ProblemListComponent,

    AssignmentDetailComponent,
    AssignmentsComponent,
    AssignmentListComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [StudentService, ProblemService, AssignmentService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
