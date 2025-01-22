import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, tap } from 'rxjs';
import { iCityResponse } from '../interfaces/icityresponse';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class CitysrvService {

  private citySubject$ = new BehaviorSubject<iCityResponse[] | null>(null);
  private cityUrl: string = `${environment.baseUrl}/cities`;

  constructor(private http: HttpClient, private router: Router) {}

  getAllCities() {
    return this.http.get<iCityResponse[]>(this.cityUrl).pipe(
      tap((cities: iCityResponse[] | null) => this.citySubject$.next(cities))
    );
  }



}
