import { AdminService } from './../../services/admin.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-get-bookings',
  templateUrl: './get-bookings.component.html',
  styleUrl: './get-bookings.component.scss',
})
export class GetBookingsComponent implements OnInit {
  isSpinning: boolean = false;
  bids: any = [];
  constructor(private AdminService: AdminService) {}
  ngOnInit(): void {
    this.getAllBids();
  }

  getAllBids() {
    this.isSpinning = true;
    this.AdminService.getAllBids().subscribe((result) => {
      this.isSpinning = false;
      console.log(result);
      this.bids = result;
    });
  }
}
