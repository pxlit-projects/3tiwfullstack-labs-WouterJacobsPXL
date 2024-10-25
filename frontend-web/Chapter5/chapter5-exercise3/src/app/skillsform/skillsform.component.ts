import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-skillsform',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './skillsform.component.html',
  styleUrl: './skillsform.component.css'
})
export class SkillsFormComponent implements OnInit {
  skillsForm: FormGroup;
  experienceLevels = ['Beginner', 'Intermediate', 'Advanced'];

  constructor(private fb: FormBuilder) {
    this.skillsForm = this.fb.group({
      skills: this.fb.array([])
    });
  }

  ngOnInit(): void {
    // Add one skill field by default
    this.addSkill();
  }

  // Getter for easy access to the skills FormArray
  get skills(): FormArray {
    return this.skillsForm.get('skills') as FormArray;
  }

  // Create a new skill FormGroup
  createSkillFormGroup(): FormGroup {
    return this.fb.group({
      skillName: ['', Validators.required],
      experienceLevel: ['Beginner', Validators.required]
    });
  }

  // Add a new skill field
  addSkill(): void {
    this.skills.push(this.createSkillFormGroup());
  }

  // Remove a skill field
  removeSkill(index: number): void {
    this.skills.removeAt(index);
  }

  // Form submission handler
  onSubmit(): void {
    if (this.skillsForm.valid) {
      console.log('Submitted Skills:', this.skillsForm.value.skills);
    } else {
      this.markFormGroupTouched(this.skillsForm);
    }
  }

  // Helper method to mark all controls as touched
  private markFormGroupTouched(formGroup: FormGroup | FormArray): void {
    Object.values(formGroup.controls).forEach(control => {
      if (control instanceof FormGroup || control instanceof FormArray) {
        this.markFormGroupTouched(control);
      } else {
        control.markAsTouched();
      }
    });
  }
}
