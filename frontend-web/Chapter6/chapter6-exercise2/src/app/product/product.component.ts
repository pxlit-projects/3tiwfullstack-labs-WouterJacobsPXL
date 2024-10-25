import {Component, inject} from '@angular/core';
import {LoggingService} from "../shared/services/logging.service";

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {
  logger: LoggingService = inject(LoggingService);
  ngOnInit(): void {
    this.logger.log("ProductComponent initialized")
  }
  handleSubmit() {
    this.logger.log("product 223 has been ordered")
  }

  showAllLogs() {
    this.logger.showAllLogs();
  }
}
