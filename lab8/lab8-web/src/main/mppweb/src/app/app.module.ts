import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here


import { AppComponent } from './app.component';
import { StudentsComponent } from './students/students.component';
import { ProblemsComponent } from './problems/problems.component';
import { AssignmentsComponent } from './assignments/assignments.component';
import { StudentDetailComponent } from './student-detail/student-detail.component';
import { ProblemDetailComponent } from './problem-detail/problem-detail.component';
import { AssignmentDetailComponent } from './assignment-detail/assignment-detail.component';
import { StudentService } from './services/student.service';
import { ProblemService } from './services/problem.service';
import { AssignmentService } from './services/assignment.service';


@NgModule({
  declarations: [
    AppComponent,
    StudentsComponent,
    ProblemsComponent,
    AssignmentsComponent,
    StudentDetailComponent,
    ProblemDetailComponent,
    AssignmentDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [StudentService, ProblemService, AssignmentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
