import { Component, ElementRef, ViewChild } from '@angular/core';
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
import { UploadSvcService } from '../../services/upload-svc.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss',
})
export class ProfileComponent {
  form!: FormGroup;

  customer!: iAppUserResponse;
  isEditing: boolean = false;

  @ViewChild('imgInput') imgInput!: ElementRef;
  previewUrl: string | null = null;
  file: File | undefined = undefined;

  constructor(
    private authSrv: AuthsrvService,
    private router: Router,
    private fb: FormBuilder,
    private uploadSvc: UploadSvcService
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

  save() {
    if (this.file) {
      this.uploadSvc.uploadImage(this.file).subscribe((data) => {
        this.form.patchValue({
          avatar: data.url,
        });
        this.sendData();
      });
    } else {
      this.sendData();
    }
  }

  sendData() {
    this.authSrv.updateAppUser(this.form.value).subscribe((data) => {
      console.log(data);
      this.isEditing = false;
    });
  }

  enableEditing() {
    if (this.isEditing) {
      this.previewUrl = null;
    }
    this.isEditing = !this.isEditing;
  }

  triggerInput() {
    if (this.isEditing && this.imgInput) {
      this.imgInput.nativeElement.click();
    }
  }
  onFileSelected(event: Event): void {
    this.file = (event.target as HTMLInputElement).files?.[0];
    if (this.file) {
      const reader = new FileReader();
      reader.onload = (e: ProgressEvent<FileReader>) => {
        this.previewUrl = e.target?.result as string;
      };
      reader.readAsDataURL(this.file);
    }
  }
}
