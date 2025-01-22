import { Component } from '@angular/core';
import { AuthsrvService } from '../../auth/authsrv.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  constructor(private authSvc: AuthsrvService) {}

  logout() {
    this.authSvc.logout();
  }
}
