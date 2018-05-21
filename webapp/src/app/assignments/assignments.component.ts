import { AssignmentService } from './shared/assignment.service';
import { Assignment } from './shared/assignment.model';
import { Component } from '@angular/core';
import { Student } from '../students/shared/student.model';
import { StudentService } from '../students/shared/student.service';

@Component({
  moduleId: module.id,
  selector: 'ubb-assignments',
  templateUrl: './assignments.component.html',
  styleUrls: ['./assignments.component.css'],
})
export class AssignmentsComponent {

  errorMessage: string;
  showProblemsAndGrades: boolean;
  assignments: Assignment[];
  selectedStudent: Student;
  
  constructor(private studentService: StudentService,
    private assignmentService: AssignmentService,
    private location: Location) {
  }

  loadDisciplinesAndGrades(serialNumber: string) {
    this.showProblemsAndGrades = false;
    if (!serialNumber) {
      console.log("serial number must not be empty");
      alert("serial number must not be empty");
      return;
    }
    this.loadStudentDisciplinesForStudent(serialNumber);
  }

  private loadStudentDisciplinesForStudent(serialNumber: string) {
  this.studentService.getStudents()
  .subscribe(
      students => {
          const studentArr = students.filter(s => s.serialNumber === serialNumber);
          //TODO handle errors (studentArr should contain one student)
          if (studentArr && studentArr.length === 1) {
              this.showProblemsAndGrades = true;
              const student = studentArr[0];
              this.selectedStudent = student;
              this.assignmentService.getAssignments(student.id)
                  .subscribe(
                      assignments => this.assignments = assignments,
                      error => this.errorMessage = error)
          } else {
              console.log("studentArr ", studentArr);
          }
      },
      error => this.errorMessage = <any>error);
  }

  save(studentDisciplineForm) {
    let grades = studentDisciplineForm.form.value;
    console.log("grades: ", grades);
    this.assignmentService.saveGrades(this.selectedStudent.id, grades)
    .subscribe(_ => this.goBack());
  }

  private goBack(): void {
    this.location.back();
  }
}
