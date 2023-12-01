import { Appointment } from "./appointment";

export class Booking {
  public user:string;
  public appointment : Appointment

  constructor(user:string, appointment : Appointment){
    this.user = user;
    this.appointment = appointment
  }
}
