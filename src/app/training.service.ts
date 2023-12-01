import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Course } from './models/course';
import { Subject } from 'rxjs';
import { Appointment } from './models/appointment';
@Injectable({
  providedIn: 'root'
})
export class TrainingService {
  constructor(private http: HttpClient){}

  coursesChanged = new Subject<Course[]>()
 
  getCourses(searchTerm:string){
    return this.http.get<Course[]>('http://localhost:5000/training/courses?name='+searchTerm);
  }

  addCourse(course:Course){
    return this.http.post<Course>('http://localhost:5000/training/course', course);
  }

  addAppointment(appointment:any){
    return this.http.post<Appointment>('http://localhost:5000/training/appointment', appointment);
  }

  bookAppointment(booking:any){
    return this.http.post<Appointment>('http://localhost:5000/training/book', booking);
  }
 
}
