import {Component, Input, OnInit} from '@angular/core';
import {ProblemService} from "../shared/problem.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';
import {Problem} from "../shared/problem.model";

import 'rxjs/add/operator/switchMap';


@Component({
  selector: 'ubb-problem-detail',
  templateUrl: './problem-detail.component.html',
  styleUrls: ['./problem-detail.component.css'],
})

export class ProblemDetailComponent implements OnInit {

  @Input() problem: Problem;

  constructor(private problemService: ProblemService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.problemService.getProblem(+params['id']))
      .subscribe(problem => this.problem = problem);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.problemService.update(this.problem)
      .subscribe(_ => this.goBack());
  }
  
}
