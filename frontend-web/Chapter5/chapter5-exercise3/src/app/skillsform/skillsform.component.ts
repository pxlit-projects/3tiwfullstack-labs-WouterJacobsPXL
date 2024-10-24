import { Component } from '@angular/core';
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
export class SkillsformComponent {
  skillsForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.skillsForm = this.fb.group({
      skills: this.fb.array([this.createSkillFormGroup()]) // Initialize with one skill group
    });
  }

  // Getter to access the skills FormArray
  get skills(): FormArray {
    return this.skillsForm.get('skills') as FormArray;
  }

  // Method to create a new skill FormGroup
  createSkillFormGroup(): FormGroup {
    return this.fb.group({
      skillName: ['', Validators.required], // Skill Name is required
      experienceLevel: ['Beginner', Validators.required] // Default to Beginner
    });
  }

  // Method to add a new skill FormGroup to the FormArray
  addSkill() {
    this.skills.push(this.createSkillFormGroup());
  }

  // Method to remove a skill FormGroup from the FormArray
  removeSkill(index: number) {
    this.skills.removeAt(index);
  }

  // On form submission, log the list of skills
  onSubmit() {
    if (this.skillsForm.valid) {
      console.log(this.skillsForm.value.skills);
    } else {
      console.log("Form is invalid");
    }
  }
}
