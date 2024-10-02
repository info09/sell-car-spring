import { Component, OnInit } from '@angular/core';
import { StorageService } from './auth/services/storage/storage.service';
import { Router } from '@angular/router';
import { AuthService } from './auth/services/auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  title = 'sellcar_angular';

  isAdminLoggedIn: Boolean = StorageService.isAdminLoggedIn();
  isCustomerLoggedIn: Boolean = StorageService.isCustomerLoggedIn();

  constructor(private router: Router, private authService: AuthService) {}
  ngOnInit(): void {
    this.router.events.subscribe((event) => {
      if (event.constructor.name === 'NavigationEnd') {
        this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
        this.isCustomerLoggedIn = StorageService.isCustomerLoggedIn();
      }
    });
  }

  logout() {
    StorageService.signOut();
    this.router.navigateByUrl('/login');
  }
}
