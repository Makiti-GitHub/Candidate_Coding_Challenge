import { Appointment } from "./appointment";

export class Course {
  public desc:string;
  public name:string;
  public lecturer:string;
  public image:string;
  public price:number;
  public appointments: Appointment[]

  constructor(desc:string, name:string, lecturer:string, price:number,appointments: Appointment[], image:string ){
    this.desc = desc
    this.name = name
    this.lecturer = lecturer
    this.price = price
    this.appointments = appointments
    this.image = image
  }
}
