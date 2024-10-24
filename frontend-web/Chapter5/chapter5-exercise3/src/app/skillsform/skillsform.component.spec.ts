import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillsformComponent } from './skillsform.component';

describe('SkillsformComponent', () => {
  let component: SkillsformComponent;
  let fixture: ComponentFixture<SkillsformComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SkillsformComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SkillsformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
