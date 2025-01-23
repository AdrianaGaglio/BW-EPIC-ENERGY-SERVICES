import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { tokenInterceptor } from './auth/token.interceptor';
import {
  HTTP_INTERCEPTORS,
  provideHttpClient,
  withInterceptors,
  withInterceptorsFromDi,
} from '@angular/common/http';
import { NgIconsModule } from '@ng-icons/core';
import {
  bootstrapArrowDown,
  bootstrapArrowUp,
  bootstrapFloppy,
  bootstrapSearch,
} from '@ng-icons/bootstrap-icons';
import { HeaderComponent } from './main-components/header/header.component';
import { FooterComponent } from './main-components/footer/footer.component';
import { ErrorInterceptor } from './interceptors/error.interceptor';

@NgModule({
  declarations: [AppComponent, HeaderComponent, FooterComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    NgIconsModule.withIcons({
      bootstrapSearch,
      bootstrapFloppy,
      bootstrapArrowUp,
      bootstrapArrowDown,
    }),
  ],
  providers: [
    provideHttpClient(withInterceptors([tokenInterceptor, ErrorInterceptor])),
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
