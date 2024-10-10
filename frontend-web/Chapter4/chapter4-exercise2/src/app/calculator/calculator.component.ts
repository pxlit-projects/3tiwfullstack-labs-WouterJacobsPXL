import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-calculator',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './calculator.component.html',
  styleUrl: './calculator.component.css'
})
export class CalculatorComponent {
  number1: string = "";
  number2: string = "";
  result: string = "";
  clicked: boolean = false;

  calculate() {
    this.result =  (parseInt(this.number1) +  parseInt(this.number2)).toString()
    this.clicked = true;
  }
}
