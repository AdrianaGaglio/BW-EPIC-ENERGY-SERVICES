import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { AuthsrvService } from '../authsrv.service';
import { Router } from '@angular/router';
import { CitysrvService } from '../../services/citysrv.service';
import { iDistrictResponse } from '../../interfaces/idistrictresponse';
import { iCityResponse } from '../../interfaces/icityresponse';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent implements OnInit {
  form: FormGroup;
  types = ['PA', 'SAS', 'SPA', 'SRL'];
  districts: iDistrictResponse[] = [];
  cities: iCityResponse[] = [];
  selected: boolean = false;
  selected1: boolean = false;

  constructor(
    private authSrv: AuthsrvService,
    private router: Router,
    private fb: FormBuilder,
    private cityService: CitysrvService
  ) {
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
          districtId: ['', Validators.required],
          idCity: ['', Validators.required],
        }),
        operationalHeadquartersAddress: this.fb.group({
          street: ['', [Validators.required]],
          addressNumber: ['', [Validators.required]],
          cap: [null, [Validators.required, Validators.min(10000), Validators.max(99999)]],
          districtId: ['', Validators.required],
          idCity: ['', Validators.required],
        }),
      }),
      avatar: [''],
    });
  }

  ngOnInit(): void {

    this.cityService.getAllDistricts().subscribe({
      next: (data) => {
        this.districts = data;
      },
      error: (error) => {
        console.error('Errore nel caricamento dei distretti:', error);
      },
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

  onDistrictChange(event: Event): void {
    const selectedValue = (event.target as HTMLSelectElement).value;
    if (selectedValue) {
      console.log(selectedValue);
      this.cityService.getCitiesByDistrictId(Number(selectedValue)).subscribe({
        next: (data) => {
          this.cities = data;
          this.selected = true;
        },
        error: (error) => {
          console.error('Errore nel caricamento delle città:', error);
        },
      });
    } else {
      this.cities = [];
    }
  }

  onDistrictChange1(event: Event): void {
    const selectedValue = (event.target as HTMLSelectElement).value;
    if (selectedValue) {
      console.log(selectedValue);
      this.cityService.getCitiesByDistrictId(Number(selectedValue)).subscribe({
        next: (data) => {
          this.cities = data;
          this.selected1 = true;
        },
        error: (error) => {
          console.error('Errore nel caricamento delle città:', error);
        },
      });
    } else {
      this.cities = [];
    }
  }
}
