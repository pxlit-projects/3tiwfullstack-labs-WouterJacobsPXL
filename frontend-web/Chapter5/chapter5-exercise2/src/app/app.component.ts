import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ContactFormComponent} from "./contact-form/contact-form.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ContactFormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'chapter5-exercise2';
}
