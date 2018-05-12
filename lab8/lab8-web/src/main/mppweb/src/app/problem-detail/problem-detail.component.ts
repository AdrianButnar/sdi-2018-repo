import { Component, OnInit, Input } from '@angular/core';
import { Problem } from '../problem';

@Component({
  selector: 'app-problem-detail',
  templateUrl: './problem-detail.component.html',
  styleUrls: ['./problem-detail.component.css']
})
export class ProblemDetailComponent implements OnInit {

  @Input() problem: Problem;

  constructor() { }

  ngOnInit() {
  }

}
