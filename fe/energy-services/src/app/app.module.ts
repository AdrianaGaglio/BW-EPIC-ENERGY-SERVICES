import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { tokenInterceptor } from './auth/token.interceptor';
import {
  provideHttpClient,
  withInterceptors,
  withInterceptorsFromDi,
} from '@angular/common/http';
import { NgIconsModule } from '@ng-icons/core';
import { bootstrapFloppy, bootstrapSearch } from '@ng-icons/bootstrap-icons';
import { HeaderComponent } from './main-components/header/header.component';
import { DirectivesModule } from './shared/directives/directives/directives.module';

@NgModule({
  declarations: [AppComponent, HeaderComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    NgIconsModule.withIcons({ bootstrapSearch, bootstrapFloppy }),
    DirectivesModule,
  ],
  providers: [provideHttpClient(withInterceptors([tokenInterceptor]))],
  bootstrap: [AppComponent],
})
export class AppModule {}
