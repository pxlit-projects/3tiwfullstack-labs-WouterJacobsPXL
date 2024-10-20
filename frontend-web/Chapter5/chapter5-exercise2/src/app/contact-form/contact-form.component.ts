import {Component, inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";

@Component({
  selector: 'app-contact-form',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.css'
})
export class ContactFormComponent {
  messageSend: boolean = false;
  fb: FormBuilder = inject(FormBuilder);
  contactForm: FormGroup = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    phoneNumber: ['', [Validators.required, Validators.minLength(10),Validators.maxLength(10)]],
    message: ['', [Validators.required, Validators.minLength(10)]]
  })

  onSubmit() {
    if (this.contactForm.valid){
      console.log(this.contactForm.value);
      this.contactForm.reset()
      this.messageSend = true;
    }else {
      this.messageSend = false;
    }
  }
}
