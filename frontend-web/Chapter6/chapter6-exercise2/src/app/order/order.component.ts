import {Component, inject, OnInit} from '@angular/core';
import {LoggingService} from "../shared/services/logging.service";

@Component({
  selector: 'app-order',
  standalone: true,
  imports: [],
  templateUrl: './order.component.html',
  styleUrl: './order.component.css'
})
export class OrderComponent implements OnInit {

  logger: LoggingService = inject(LoggingService);

  ngOnInit(): void {
   this.logger.log("OrderComponent initialized")
  }

  handleSubmit() {
    this.logger.log("order 123 has been ordered")
  }
}
