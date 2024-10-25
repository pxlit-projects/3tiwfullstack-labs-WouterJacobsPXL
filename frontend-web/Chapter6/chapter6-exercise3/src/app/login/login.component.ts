import { Component, inject } from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email: string = "";
  password: string = "";
  authenticator: AuthService = inject(AuthService)

  onSubmit(loginForm: NgForm) {
    if (loginForm.valid) {
      if (this.authenticator.authenticate(this.email, this.password)) {
        localStorage.setItem('email', this.email);
        localStorage.setItem('password', this.password);
      }else{
        window.alert("Wrong credentials");
      }
    }
  }
}
