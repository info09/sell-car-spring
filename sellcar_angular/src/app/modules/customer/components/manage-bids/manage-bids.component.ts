import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-manage-bids',
  templateUrl: './manage-bids.component.html',
  styleUrl: './manage-bids.component.scss',
})
export class ManageBidsComponent implements OnInit {
  id: number = this.activatedRoute.snapshot.params['id'];

  bids: any = [];
  isSpinning: boolean = false;

  constructor(
    private customerService: CustomerService,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.getBidByCarId();
  }

  getBidByCarId() {
    this.customerService.getBidByCardId(this.id).subscribe((result) => {
      console.log(result);
      this.isSpinning = false;
      this.bids = result;
    });
  }
}
