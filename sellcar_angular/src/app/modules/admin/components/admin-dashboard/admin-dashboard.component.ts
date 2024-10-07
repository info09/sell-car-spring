import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.scss',
})
export class AdminDashboardComponent implements OnInit {
  cars: any = [];
  constructor(private adminService: AdminService) {}
  ngOnInit(): void {
    this.getCar();
  }

  getCar() {
    this.adminService.getAllCars().subscribe((result) => {
      this.cars = result;
    });
  }
}
