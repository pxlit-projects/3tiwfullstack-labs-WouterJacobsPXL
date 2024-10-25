import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoggingService {
  logs: string[] = new Array<string>();

  log(message: string): void {
    const now: Date = new Date();
    const formattedDate: string = `${now.getFullYear()}-${(now.getMonth() + 1).toString().padStart(2, '0')}-${now.getDate().toString().padStart(2, '0')}`;
    const formattedTime: string = `${now.getHours().toString().padStart(2, '0')}:${now.getMinutes().toString().padStart(2, '0')}:${now.getSeconds().toString().padStart(2, '0')}`;

    const loggedMessage: string = `${message} on ${formattedDate} at ${formattedTime}`
    this.logs.push(loggedMessage);
    console.log(loggedMessage);
  }

  showAllLogs(): void{
    console.log(this.logs);
  }
}
