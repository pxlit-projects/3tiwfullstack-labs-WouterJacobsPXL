import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoItemsListComponent } from './todo-items-list.component';

describe('TodoItemsListComponent', () => {
  let component: TodoItemsListComponent;
  let fixture: ComponentFixture<TodoItemsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TodoItemsListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TodoItemsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
