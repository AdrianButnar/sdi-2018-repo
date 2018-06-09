import {Component, OnInit} from '@angular/core';
import {Problem} from '../shared/problem.model';
import {ProblemService} from '../shared/problem.service';
import {Router} from '@angular/router';


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


  add(problemNumber: string, problemText: string): void {
    problemNumber = problemNumber.trim();
    problemText = problemText.trim();
    if (!problemNumber) { return; }
    if (!problemText) { return; }
    var numProb = parseInt(problemNumber, 10);

    console.log(numProb + " " + problemText);
    const newProb = <Problem>({
      number:numProb,
      text:problemText
    });
    console.log(newProb);
    this.problemService.addProblem(newProb)
      .subscribe(problem => {
        this.problems.push(problem);
      });
  }

  delete(problem: Problem): void {
    this.problems = this.problems.filter(h => h !== problem);
    this.problemService.deleteProblem(problem).subscribe();
  }

}
