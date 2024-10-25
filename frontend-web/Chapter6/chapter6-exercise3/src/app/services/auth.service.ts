import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authenticate(email: string, password: string):boolean {
    return email === "admin@example.com" && password === "password123";
  }
}
