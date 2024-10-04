import { Component } from '@angular/core';
import {TodoItemComponent} from "../todo-item/todo-item.component";

@Component({
  selector: 'app-todo-items-list',
  standalone: true,
  imports: [
    TodoItemComponent
  ],
  templateUrl: './todo-items-list.component.html',
  styleUrl: './todo-items-list.component.css'
})
export class TodoItemsListComponent {

}
