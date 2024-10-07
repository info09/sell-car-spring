import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-my-cars',
  templateUrl: './my-cars.component.html',
  styleUrl: './my-cars.component.scss',
})
export class MyCarsComponent implements OnInit {
  cars: any = [];
  constructor(private customerService: CustomerService) {}
  ngOnInit(): void {
    this.getMyCars();
  }

  getMyCars() {
    this.customerService.getMyCars().subscribe((result) => {
      console.log(result);
      this.cars = result;
    });
  }
}
