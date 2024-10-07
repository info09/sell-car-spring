import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-my-cars',
  templateUrl: './my-cars.component.html',
  styleUrl: './my-cars.component.scss',
})
export class MyCarsComponent implements OnInit {
  cars: any = [];
  constructor(
    private customerService: CustomerService,
    private messageService: NzMessageService
  ) {}
  ngOnInit(): void {
    this.getMyCars();
  }

  getMyCars() {
    this.customerService.getMyCars().subscribe((result) => {
      console.log(result);
      this.cars = result;
    });
  }

  deleteCar(id) {
    this.customerService.deleteCar(id).subscribe((result) => {
      this.messageService.success('Delete successfully', { nzDuration: 5000 });
      this.getMyCars();
    });
  }
}
