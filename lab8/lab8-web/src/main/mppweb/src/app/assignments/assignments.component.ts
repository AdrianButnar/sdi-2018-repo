import { ASSIGNMENTS } from './../mock-assignment';
import { Assignment } from './../assignment';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-assignments',
  templateUrl: './assignments.component.html',
  styleUrls: ['./assignments.component.css']
})
export class AssignmentsComponent implements OnInit {

  assignments = ASSIGNMENTS;
  constructor() { }

  ngOnInit() {
  }

  selectedAssignment: Assignment;

  onSelect(assignment: Assignment): void {
    this.selectedAssignment = assignment;
  }
}
