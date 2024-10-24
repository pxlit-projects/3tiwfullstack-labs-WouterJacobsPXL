import {Component, inject, OnInit} from '@angular/core';
import {CustomerItemComponent} from "../customer-item/customer-item.component";
import {FilterComponent} from "../filter/filter.component";
import {Customer} from "../../../shared/models/customer.model";
import {Filter} from "../../../shared/models/Filter.model";
import {AddCustomerComponent} from "../add-customer/add-customer.component";
import {CustomerService} from "../../../shared/services/customer.service";

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
  customerService: CustomerService = inject(CustomerService);

  ngOnInit(): void {
    this.fetchData();
    this.customers[1].isLoyal = true;
    this.filteredData = this.customers;
  }

  handleFilter(filter: Filter){
    this.customerService.filterCustomers(filter).subscribe({
      next: customers => this.filteredData = customers
    });
  }

  private isCustomerMatchingFilter(customer: Customer, filter: Filter): boolean {
    const matchesName = customer.name.toLowerCase().includes(filter.name.toLowerCase());
    const matchesCity = customer.city.toLowerCase().includes(filter.city.toLowerCase());
    const matchesVat = filter.vat ? customer.vat === filter.vat : true;

    return matchesName && matchesCity && matchesVat;
  }

  processAdd(customer: Customer){
    this.customerService.addCustomer(customer).subscribe({
      next: () => {
        this.customerService.getCustomers().subscribe({
          next: customers => this.filteredData = customers
        });
      }
    });
  }
  fetchData(): void {
    this.customerService.getCustomers().subscribe({
      next: customers => {
        this.filteredData = customers;
      }
    });
  }
}
