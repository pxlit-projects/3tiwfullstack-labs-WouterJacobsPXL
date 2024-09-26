import { Component } from '@angular/core';
import {CustomerItemComponent} from "../customer-item/customer-item.component";
import {FilterComponent} from "../filter/filter.component";

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [
    CustomerItemComponent,
    FilterComponent
  ],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.css'
})
export class CustomerListComponent {

}
