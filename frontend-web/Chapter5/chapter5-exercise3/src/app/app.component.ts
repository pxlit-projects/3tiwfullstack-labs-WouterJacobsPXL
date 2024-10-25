import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {SkillsFormComponent} from "./skillsform/skillsform.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SkillsFormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'chapter5-exercise3';
}
