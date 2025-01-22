import { Router } from '@angular/router';
import { AuthsrvService } from './authsrv.service';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { iLoginRequest } from './interfaces/i-login-request';
import { DecodeTokenService } from '../services/decode-token.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss',
})
export class AuthComponent {
  form: FormGroup;

  constructor(
    private authSvc: AuthsrvService,
    private router: Router,
    private decodeToken: DecodeTokenService
  ) {
    this.form = new FormGroup({
      identifier: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
    });
  }

  login() {
    if (this.form.valid) {
      //prendo i dati dal form e li inserisco in una varabile
      const formData: iLoginRequest = this.form.value;
      this.authSvc.login(formData).subscribe({
        next: (data) => {
          console.log('login effettuato con successo');
          this.decodeToken.userRoles$.next(this.decodeToken.getRoles());
          this.router.navigate(['home']);
        },
        error: (data) => {
          console.log('errore login');
        },
      });
    } else {
      console.log('form invalido');
    }
  }
}
