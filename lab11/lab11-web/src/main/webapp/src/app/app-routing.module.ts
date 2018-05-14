import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {StudentsComponent} from "./students/students.component";
import {StudentDetailComponent} from "./students/student-detail/student-detail.component";
import {ProblemsComponent} from "./problems/problems.component";
import {ProblemDetailComponent} from "./problems/problem-detail/problem-detail.component";

import {AssignmentsComponent} from "./assignments/assignments.component";
import {AssignmentDetailComponent} from "./assignments/assignment-detail/assignment-detail.component";


const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'students', component: StudentsComponent},
  {path: 'student/detail/:id', component: StudentDetailComponent},

  {path: 'problems', component: ProblemsComponent},
  {path: 'problem/detail/:id', component: ProblemDetailComponent},

  {path: 'assignments', component: AssignmentsComponent},
  {path: 'assignment/detail/:id', component: AssignmentDetailComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
