import { Component } from '@angular/core';
import { AuthsrvService } from '../authsrv.service';
import { Router } from '@angular/router';
import { CitysrvService } from '../../services/citysrv.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-registeruser',
  templateUrl: './registeruser.component.html',
  styleUrl: './registeruser.component.scss'
})
export class RegisteruserComponent {

    form: FormGroup;
    types = ['PA', 'SAS', 'SPA', 'SRL'];
    formvalid = false;

  constructor(
        private authSrv: AuthsrvService,
        private router: Router,
        private fb: FormBuilder,
        private cityService: CitysrvService,
  ){
    this.form = this.fb.group({
      name: ['', [Validators.required]],
      surname: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      username: ['', [Validators.required]],
    });
  }

  register(): void {
    if (this.form.valid) {
      console.log('Dati inviati:', this.form.value);
      this.authSrv.register(this.form.value).subscribe({
        next: (data) => {
          console.log('Registrazione effettuata con successo:', data);
          this.router.navigate(['home']);
        },
        error: (error) => {
          console.error('Errore nella registrazione:', error);
        },
      });
    } else {
      console.error('Form non valido:', this.form.errors);
    }
  }

  isValid(fieldName: string) {
    return this.form.get(fieldName)?.valid;
  }

  isTouched(fieldName: string) {
    return this.form.get(fieldName)?.touched;
  }

  isInValidTouched(fieldName: string) {
    return !this.isValid(fieldName) && this.isTouched(fieldName);
  }


}
