import {Component, OnInit} from '@angular/core';
import {Student} from "../shared/student.model";
import {StudentService} from "../shared/student.service";
import {Router} from "@angular/router";


@Component({
  moduleId: module.id,
  selector: 'ubb-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css'],
})
export class StudentListComponent implements OnInit {
  errorMessage: string;
  students: Array<Student>;
  selectedStudent: Student;

  constructor(private studentService: StudentService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getStudents();
  }

  getStudents() {
    this.studentService.getStudents()
      .subscribe(
        students => this.students = students,
        error => this.errorMessage = <any>error
      );
  }

  onSelect(student: Student): void {
    this.selectedStudent = student;
  }

  gotoDetail(): void {
    this.router.navigate(['/student/detail', this.selectedStudent.id]);
  }

  add(name: string, serialNumber: string): void {
    name = name.trim();
    serialNumber = serialNumber.trim();
    if (!name) { return; }
    if (!serialNumber) { return; }
    this.studentService.addStudent({ serialNumber, name } as Student)
      .subscribe(student => {
        this.students.push(student);
      });
  }

  delete(student: Student): void {
    this.students = this.students.filter(h => h !== student);
    this.studentService.deleteStudent(student).subscribe();
  }

}
