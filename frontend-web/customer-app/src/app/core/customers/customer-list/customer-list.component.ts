import {Component, OnInit} from '@angular/core';
import {CustomerItemComponent} from "../customer-item/customer-item.component";
import {FilterComponent} from "../filter/filter.component";
import {Customer} from "../../../shared/models/customer.model";
import {Filter} from "../../../shared/models/Filter.model";
import {AddCustomerComponent} from "../add-customer/add-customer.component";

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [
    CustomerItemComponent,
    FilterComponent,
    AddCustomerComponent
  ],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.css'
})

export class CustomerListComponent implements OnInit {
  customers!: Customer[];
  filteredData!: Customer[];

  ngOnInit(): void {
    this.customers = [
      new Customer('Dries Swinnen', 'dries@pxl.be', 'Pelt', 'mystreet', 'Belgium', 21),
      new Customer('John Doe', 'john@doe.be', 'New York', '5th Avenue', 'USA', 6),
      new Customer('Jane Doe', 'jane@doe.be', 'Los Angeles', 'Sunset Boulevard', 'USA', 6)
    ];

    this.customers[1].isLoyal = true;
    this.filteredData = this.customers;
  }

  handleFilter(filter: Filter){
    this.filteredData = this.customers.filter(customer => this.isCustomerMatchingFilter(customer, filter));
  }

  private isCustomerMatchingFilter(customer: Customer, filter: Filter): boolean {
    const matchesName = customer.name.toLowerCase().includes(filter.name.toLowerCase());
    const matchesCity = customer.city.toLowerCase().includes(filter.city.toLowerCase());
    const matchesVat = filter.vat ? customer.vat === filter.vat : true;

    return matchesName && matchesCity && matchesVat;
  }

  processAdd(customer: Customer){
    this.customers.push(customer);
    this.filteredData = this.customers;
  }
}
