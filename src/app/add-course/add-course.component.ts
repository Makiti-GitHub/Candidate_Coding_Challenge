import { Component, OnInit } from '@angular/core';
import { TrainingService } from '../training.service';
import { Course } from '../models/course';
import { ToastrService } from 'ngx-toastr';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Appointment } from '../models/appointment';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit{
  courses:Course[] = []
  form: FormGroup;
  formAppointment: FormGroup;
  openModal:boolean = false
  courseId:string = ""
  constructor(private trainingService: TrainingService,   private toastr: ToastrService,){
    this.form = new FormGroup({
      name: new FormControl(null, [
        Validators.required,
      ]),
      price: new FormControl(null, Validators.required),
      image: new FormControl(null, Validators.required),
      lecturer: new FormControl(null, Validators.required),
      desc: new FormControl(null, Validators.required),
    });

    this.formAppointment = new FormGroup({
      date: new FormControl(null, Validators.required),
    })
  }

  onOpenModal(course:any){
    this.openModal = !this.openModal
    if(course){
      this.courseId = course._id
    }

  }

  onSubmitApp(course:any){
    console.log(course)
    const form = this.formAppointment.value
    let appointment = {
      course : this.courseId,
      date: form.date
    }
    this.trainingService.addAppointment(appointment).subscribe({
      next: (receivedApp: Appointment) => {
        this.toastr.success("created")
        setTimeout(() => {
          
          this.openModal = false
        }, 2000);
      },
      error: (e) => {
        console.log(e)
        this.toastr.error(e.error.message);
      },
    });
  }

  ngOnInit(): void {
  
    this.trainingService.getCourses("").subscribe({
      next: (receivedCourses: Course[]) => {
        this.courses =  receivedCourses;
      },
      error: (e) => {
        this.toastr.error('An error occured!');
      },
    });
  
  }

  onSubmit(){
    const form = this.form.value
    const appointment:Appointment[] = []
    const course = new Course(form.desc,form.name,form.lecturer,form.price,appointment,form.image)

    this.trainingService.addCourse(course).subscribe({
      next: (receivedCourse: Course) => {
        this.toastr.success("created")
        this.courses.push(receivedCourse)
      },
      error: (e) => {
        console.log(e)
        this.toastr.error(e.error.message);
      },
    });
  }
  
}
