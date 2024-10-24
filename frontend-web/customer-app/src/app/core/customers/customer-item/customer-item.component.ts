import {Component, Input} from '@angular/core';
import {Customer} from "../../../shared/models/customer.model";
import {NgClass} from "@angular/common";
import {RouterLink, RouterLinkActive} from "@angular/router";

@Component({
  selector: 'app-customer-item',
  standalone: true,
  imports: [
    NgClass,
    RouterLink,
    RouterLinkActive
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
