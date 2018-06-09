import {Component, OnInit} from '@angular/core';
import {Assignment} from '../shared/assignment.model';
import {AssignmentService} from '../shared/assignment.service';
import {Router} from '@angular/router';


@Component({
  moduleId: module.id,
  selector: 'ubb-assignment-list',
  templateUrl: './assignment-list.component.html',
  styleUrls: ['./assignment-list.component.css'],
})
export class AssignmentListComponent implements OnInit {

  assignments: Array<Assignment>;
  selectedAssignment: Assignment;


  constructor(private assignmentService: AssignmentService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.assignmentService.getAssignments()
      .subscribe(assignments => this.assignments = assignments);
  }

  onSelect(assignment: Assignment): void {
    this.selectedAssignment = assignment;
  }

  gotoDetails(): void {
    //console.log("gotoDetails");
    this.router.navigate(['/assignment/detail', this.selectedAssignment.id]);
  }

  add(studentId: string, problemId: string): void {
    studentId = studentId.trim();
    problemId = problemId.trim();
    if (!studentId) { return; }
    if (!problemId) { return; }
    var numStud = parseInt(studentId, 10);
    var numProb = parseInt(problemId, 10);
    const newAssignment = <Assignment>({
      studentId:numStud,
      problemId:numProb
    })
    this.assignmentService.addAssignment(newAssignment)
      .subscribe(assignment => {
        this.assignments.push(assignment);
      });
  }

  delete(assignment: Assignment): void {
    this.assignments = this.assignments.filter(h => h !== assignment);
    this.assignmentService.deleteAssignment(assignment).subscribe();
  }
}
