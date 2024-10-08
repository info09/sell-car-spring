import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-dashboard',
  templateUrl: './customer-dashboard.component.html',
  styleUrl: './customer-dashboard.component.scss',
})
export class CustomerDashboardComponent implements OnInit {
  cars: any = [];
  analytics: any;
  constructor(private customerService: CustomerService) {}
  ngOnInit(): void {
    this.getCar();
    this.getAnalytics();
  }

  getCar() {
    this.customerService.getAllCars().subscribe((result) => {
      console.log(result);
      this.cars = result;
    });
  }

  getAnalytics() {
    this.customerService.getAnalytics().subscribe((result) => {
      console.log(result);
      this.analytics = result;
    });
  }

  gridStyle = {
    with: '50%',
    textAlign: 'center',
  };
}
