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
import {
  bootstrapArrowDown,
  bootstrapArrowUp,
  bootstrapFloppy,
  bootstrapSearch,
} from '@ng-icons/bootstrap-icons';
import { HeaderComponent } from './main-components/header/header.component';

@NgModule({
  declarations: [AppComponent, HeaderComponent],
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
  providers: [provideHttpClient(withInterceptors([tokenInterceptor]))],
  bootstrap: [AppComponent],
})
export class AppModule {}
