import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { AuthComponent } from './auth.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbProgressbarModule } from '@ng-bootstrap/ng-bootstrap';
import { RegisteruserComponent } from './registeruser/registeruser.component';

@NgModule({
  declarations: [AuthComponent, RegisterComponent, RegisteruserComponent],
  imports: [CommonModule, AuthRoutingModule, ReactiveFormsModule, NgbProgressbarModule, FormsModule],
})
export class AuthModule {}
