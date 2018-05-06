import {Component, OnInit} from "@angular/core";
import {Problem} from "../shared/problem.model";
import {ProblemService} from "../shared/problem.service";
import {Router} from "@angular/router";


@Component({
  moduleId: module.id,
  selector: 'ubb-problem-list',
  templateUrl: './problem-list.component.html',
  styleUrls: ['./problem-list.component.css'],
})
export class ProblemListComponent implements OnInit {

  problems: Array<Problem>;
  selectedProblem: Problem;


  constructor(private problemService: ProblemService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.problemService.getProblems()
      .subscribe(problems => this.problems = problems);
  }

  onSelect(problem: Problem): void {
    this.selectedProblem = problem;
  }

  gotoDetails(): void {
    //console.log("gotoDetails");
    this.router.navigate(['/problem/detail', this.selectedProblem.id]);

  }
}
