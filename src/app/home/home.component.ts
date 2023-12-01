import { Component, OnInit } from '@angular/core';
import { Course } from '../models/course';
import { TrainingService } from '../training.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  courses: Course[] = [];
  searchTerm: string = '';
  openBoook:boolean = false;
  constructor(
    private trainingService: TrainingService,
    private router: Router,
    private toastr: ToastrService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.onGetCourses("")
  }

onOpenBook(){
  this.openBoook = !this.openBoook
}

  onGetCourses(searchEvent:string) {

    this.trainingService.getCourses(searchEvent).subscribe({
      next: (receivedCourses: Course[]) => {
        console.log(receivedCourses)
        this.courses =  receivedCourses;
      },
      error: (e) => {
        this.toastr.error('An error occured!');
      },
    });

  }

}
