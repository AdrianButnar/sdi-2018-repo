import { STUDENTS } from './../mock-students';
import { Student } from './../student';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {

  students = STUDENTS;

  constructor() { }

  ngOnInit() {
  }

  selectedStudent: Student;

  onSelect(student: Student): void {
    this.selectedStudent = student;
  }

}
