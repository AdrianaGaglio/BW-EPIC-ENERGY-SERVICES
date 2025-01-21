import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { AuthsrvService } from '../authsrv.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  form: FormGroup;
  types = ['PA', 'SAS', 'SPA', 'SRL'];

  constructor(private authSrv: AuthsrvService, private router: Router, private fb: FormBuilder) {
    this.form = this.fb.group({
      name: ['', [Validators.required]],
      surname: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      username: ['', [Validators.required]],
      customer: this.fb.group({
        denomination: ['', [Validators.required]],
        vatCode: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
        pec: ['', [Validators.required, Validators.email]],
        phone: ['', [Validators.required]],
        contactPhone: ['', [Validators.required]],
        type: ['', [Validators.required]],
        registeredOfficeAddress: this.fb.group({
          street: ['', [Validators.required]],
          addressNumber: ['', [Validators.required]],
          cap: [null, [Validators.required, Validators.min(10000), Validators.max(99999)]],
          idCity: [null, [Validators.required]],
        }),
        operationalHeadquartersAddress: this.fb.group({
          street: ['', [Validators.required]],
          addressNumber: ['', [Validators.required]],
          cap: [null, [Validators.required, Validators.min(10000), Validators.max(99999)]],
          idCity: [null, [Validators.required]],
        }),
      }),
      avatar: ['']
    });
  }

  register(): void {
    if (this.form.valid) {
      console.log(this.form.value);
      this.authSrv.register(this.form.value).subscribe({
        next: (data) => {
          console.log('Registrazione effettuata con successo:', data);
          this.router.navigate(['home']);
        },
        error: (error) => {
          console.error('Errore nella registrazione:', error);
        }
      });
    } else {
      console.error('Form non valido:', this.form.errors);
    }
  }
}
