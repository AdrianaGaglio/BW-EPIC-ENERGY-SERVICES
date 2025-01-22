import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomersRoutingModule } from './customers-routing.module';
import { CustomersComponent } from './customers.component';
import { LoggedCustomersComponent } from './components/logged-customers/logged-customers.component';
import { ForadminuserComponent } from './components/foradminuser/foradminuser.component';
import { FormsModule } from '@angular/forms';
import { SearchByCreationdateComponent } from './components/search-by-creationdate/search-by-creationdate.component';
import { NgIconsModule } from '@ng-icons/core';
import { SearchByLastcontactComponent } from './components/search-by-lastcontact/search-by-lastcontact.component';
import { SearchByDenominationComponent } from './components/search-by-denomination/search-by-denomination.component';
import { SearchByYearlyturnoverComponent } from './components/search-by-yearlyturnover/search-by-yearlyturnover.component';
import { SearchByVatcodeComponent } from './components/search-by-vatcode/search-by-vatcode.component';

@NgModule({
  declarations: [
    CustomersComponent,
    LoggedCustomersComponent,
    ForadminuserComponent,
    SearchByCreationdateComponent,
    SearchByLastcontactComponent,
    SearchByDenominationComponent,
    SearchByYearlyturnoverComponent,
    SearchByVatcodeComponent,
  ],
  imports: [CommonModule, CustomersRoutingModule, FormsModule, NgIconsModule],
})
export class CustomersModule {}
