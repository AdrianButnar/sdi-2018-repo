import {Component, Input, OnInit} from '@angular/core';
import {AssignmentService} from "../shared/assignment.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';
import {Assignment} from "../shared/assignment.model";

import 'rxjs/add/operator/switchMap';


@Component({
  selector: 'ubb-assignment-detail',
  templateUrl: './assignment-detail.component.html',
  styleUrls: ['./assignment-detail.component.css'],
})

export class AssignmentDetailComponent implements OnInit {

  @Input() assignment: Assignment;

  constructor(private assignmentService: AssignmentService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.assignmentService.getAssignment(+params['id']))
      .subscribe(assignment => this.assignment = assignment);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.assignmentService.update(this.assignment)
      .subscribe(_ => this.goBack());
  }
}
