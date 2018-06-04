import { Location } from '@angular/common';
import { Component} from '@angular/core';
import { Student } from '../students/shared/student.model';
import { Problem } from '../problems/shared/problem.model';
import { StudentService } from '../students/shared/student.service';
import { ProblemService } from '../problems/shared/problem.service';

@Component({
  moduleId: module.id,
  selector: 'app-problem-assign',
  templateUrl: './problem-assign.component.html',
  styleUrls: ['./problem-assign.component.css']
})
export class ProblemAssignComponent {

  errorMessage: string;
  selectedStudent: Student;
  showProblems: boolean;
  selectedProblems: Problem[];
  availableProblems: Problem[];

  constructor(private studentService: StudentService,
              private problemService: ProblemService,
              private location: Location) { }


  goBack(): void {
    this.location.back();
  }

  loadProblems(serialNumber: string) {
    this.showProblems = false;
    if (!serialNumber) {
      console.log("serial number must not be empty");
      alert("serial number must not be empty");
      return;
    }
    this.loadAvailableProblems();
    this.loadSelectedProblems(serialNumber);
  }

  private loadSelectedProblems(serialNumber: string) {
    this.studentService.getStudents()
      .subscribe(
        students => {
          const currentStudent = students.filter(s => s.serialNumber === serialNumber);
          //TODO there should be exactly one student in currentStudent or err; handle errs
          if (currentStudent.length === 1) {
            this.selectedStudent = currentStudent[0];
            this.showProblems = true;
            this.loadSelectedProblemsForStudent(this.selectedStudent);
          }
        },
        error => this.errorMessage = <any>error);
  }

  private loadSelectedProblemsForStudent(student: Student) {
    this.selectedProblems = [];
    const problemIds = student.problems;
    if (problemIds) {
      problemIds.forEach(id => {
        this.problemService.getProblem(id)
          .subscribe(
            problem => {
              this.selectedProblems = this.selectedProblems
                .concat([problem]);
            },
            error => this.errorMessage = error);
      });
    }
  }

  private loadAvailableProblems() {
    this.problemService.getProblems()
      .subscribe(
        problems => this.availableProblems = problems,
        error => this.errorMessage = <any>error);
  }

  selectProblem(problem: Problem) {
    const disc = this.selectedProblems.filter(d => d === problem);
    if (disc.length > 0) {
      console.log("problem already selected");
      alert("problem already selected");
      return;
    }
    this.selectedProblems = this.selectedProblems.concat([problem]);
  }

  removeSelectedProblem(problem: Problem) {
    this.selectedProblems = this.selectedProblems.filter(d => d !== problem);
  }

  save() {
    this.selectedStudent.problems = this.selectedProblems.map(d => d.id);
    console.log("selectedStudent ", this.selectedStudent);

    this.studentService.update(this.selectedStudent)
      .subscribe(_ => this.goBack());
  }
}
