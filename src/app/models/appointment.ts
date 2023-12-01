import { Booking } from "./booking";
import { Course } from "./course";

export class Appointment {
  public course: Course;
  public date:Date;
  public count:number;
  public bookings: Booking[];

  constructor(course: Course, date:Date, count:number, bookings: Booking[]){
    this.course = course;
    this.date = date;
    this.count = count;
    this.bookings = bookings
  }
}
