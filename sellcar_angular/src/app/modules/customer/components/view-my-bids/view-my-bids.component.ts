import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-view-my-bids',
  templateUrl: './view-my-bids.component.html',
  styleUrl: './view-my-bids.component.scss',
})
export class ViewMyBidsComponent implements OnInit {
  bids: any = [];
  isSpinning: boolean = false;

  constructor(
    private customerService: CustomerService,
    private message: NzMessageService
  ) {}

  ngOnInit(): void {
    this.getByBid();
  }

  getByBid() {
    this.isSpinning = true;
    this.customerService.getMyBid().subscribe((result) => {
      this.isSpinning = false;
      this.bids = result;
    });
  }
}
