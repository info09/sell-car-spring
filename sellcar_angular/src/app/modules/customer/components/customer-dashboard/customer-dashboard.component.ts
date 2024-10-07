import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-customer-dashboard',
  templateUrl: './customer-dashboard.component.html',
  styleUrl: './customer-dashboard.component.scss',
})
export class CustomerDashboardComponent implements OnInit {
  cars: any = [];
  constructor(private customerService: CustomerService) {}
  ngOnInit(): void {
    this.getCar();
  }

  getCar() {
    this.customerService.getAllCars().subscribe((result) => {
      console.log(result);
      this.cars = result;
    });
  }
}
