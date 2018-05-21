
export class Assignment {
  studentId: number;
  problemId: number;
  problemText: string;
  grade: number;

  constructor(studentId: number, problemId: number, problemText: string, grade: number) {
    this.studentId = studentId;
    this.problemId = problemId;
    this.problemText = problemText;
    this.grade = grade;
}

}
