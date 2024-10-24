import { CanDeactivateFn } from '@angular/router';
import { AddCustomerComponent } from './core/customers/add-customer/add-customer.component';

export const confirmLeaveGuard: CanDeactivateFn<AddCustomerComponent> = (component, currentRoute, currentState, nextState) => {
  if(component.customerForm.dirty){
    return window.confirm("Are you sure you want to leave this page?");
  }else{
    return true;
  }
};
