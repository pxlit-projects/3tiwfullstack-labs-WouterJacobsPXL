import {Component, Input} from '@angular/core';
import {Customer} from "../../../shared/models/customer.model";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-customer-item',
  standalone: true,
  imports: [
    NgClass
  ],
  templateUrl: './customer-item.component.html',
  styleUrl: './customer-item.component.css'
})

export class CustomerItemComponent {
  @Input() customer!: Customer;

  getDetails(): void{
    console.log(this.customer);
  }
}
