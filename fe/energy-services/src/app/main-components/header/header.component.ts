import { Component, OnInit } from '@angular/core';
import { AuthsrvService } from '../../auth/authsrv.service';
import { DecodeTokenService } from '../../services/decode-token.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent implements OnInit {

  constructor(private authSvc: AuthsrvService, private decodeToken: DecodeTokenService) {}
  roles: string[] = [];
  private rolesSubscription!: Subscription;
  isActive:boolean = false;


  ngOnInit(): void {
    this.rolesSubscription = this.decodeToken.userRoles$.subscribe((roles) => {
      this.roles = roles || [];
    });
  }

  logout() {
    this.authSvc.logout();
  }
  toggleActive() {
    this.isActive = !this.isActive;
  }
}
