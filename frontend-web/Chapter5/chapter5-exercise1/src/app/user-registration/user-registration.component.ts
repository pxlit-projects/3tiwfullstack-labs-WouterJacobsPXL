import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-user-registration',
  standalone: true,
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './user-registration.component.html',
  styleUrl: './user-registration.component.css'
})
export class UserRegistrationComponent {
  model: User = {name: '', email: '', password: ''};
  confirmedPassword: string = "";
  incorrectPasswordGiven: boolean = false;

  onSubmit(filterForm: any) {
    console.log(filterForm.valid);
    if (filterForm) {
      console.log(filterForm.password + " " + this.confirmedPassword);
      if (this.model.password !== this.confirmedPassword) {
        this.incorrectPasswordGiven = true;
        this.confirmedPassword = "";
        this.model.password = "";
        return;
      }
      this.incorrectPasswordGiven = false;
      console.log(this.model);
    }
  }
}

class User {
  name: string;
  email: string;
  password: string;


  constructor(name: string, email: string, password: string) {
    this.name = name;
    this.email = email;
    this.password = password;
  }
}
