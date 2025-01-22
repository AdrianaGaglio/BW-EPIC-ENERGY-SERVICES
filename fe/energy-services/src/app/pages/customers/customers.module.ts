import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomersRoutingModule } from './customers-routing.module';
import { CustomersComponent } from './customers.component';
import { LoggedCustomersComponent } from './components/logged-customers/logged-customers.component';
import { ForadminuserComponent } from './components/foradminuser/foradminuser.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    CustomersComponent,
    LoggedCustomersComponent,
    ForadminuserComponent,
  ],
  imports: [CommonModule, CustomersRoutingModule, FormsModule],
})
export class CustomersModule {}
