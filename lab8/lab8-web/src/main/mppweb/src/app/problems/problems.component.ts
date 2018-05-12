import { PROBLEMS } from './../mock-problems';
import { Problem } from './../problem';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-problems',
  templateUrl: './problems.component.html',
  styleUrls: ['./problems.component.css']
})
export class ProblemsComponent implements OnInit {

  prbs = PROBLEMS;

  constructor() { }

  ngOnInit() {
  }


  selectedProblem: Problem;

  onSelect(problem: Problem): void {
    this.selectedProblem = problem;
  }
}
