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
}
