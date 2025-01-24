import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthsrvService } from '../../auth/authsrv.service';
import { iCityResponse } from '../../interfaces/icityresponse';
import { iDistrictResponse } from '../../interfaces/idistrictresponse';
import { CitysrvService } from '../../services/citysrv.service';
import { DecodeTokenService } from '../../services/decode-token.service';
import { iCustomer } from '../../interfaces/icustomer';
import { CustomerService } from '../../services/customer.service';
import { iCustomerWithAppUser } from '../../interfaces/icustomerWithAppUser';
import { iAppUserResponse } from '../../auth/interfaces/i-appUserResponse';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss',
})
export class ProfileComponent {
  form!: FormGroup;

  customer!: iAppUserResponse;
  isEditing: boolean = false;

  constructor(
    private authSrv: AuthsrvService,
    private router: Router,
    private fb: FormBuilder
  ) {
    if (this.authSrv.userAuthSubject$) {
      authSrv.getByCustomerWithAppUser().subscribe((data) => {
        this.customer = data;
        this.form = this.fb.group({
          id: [this.customer.id, [Validators.required]],
          name: [this.customer.name, [Validators.required]],
          surname: [this.customer.surname, [Validators.required]],
          email: [this.customer.email, [Validators.required, Validators.email]],
          avatar: [
            this.customer.avatar,
            [Validators.required, Validators.minLength(6)],
          ],
          username: [this.customer.username, [Validators.required]],
        });
      });
    } else {
      this.router.navigate(['auth']);
    }
  }

  ngOnInit(): void {}

  edit() {
    this.authSrv.updateAppUser(this.form.value).subscribe((data) => {
      console.log(data);
    });
  }

  enableEditing() {
    this.isEditing = !this.isEditing;
  }
}
