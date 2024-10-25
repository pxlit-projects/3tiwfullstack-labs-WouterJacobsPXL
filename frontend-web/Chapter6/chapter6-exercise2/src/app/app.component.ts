import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {OrderComponent} from "./order/order.component";
import {ProductComponent} from "./product/product.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, OrderComponent, ProductComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'chapter6-exercise2';
}
