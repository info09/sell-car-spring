import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-book-a-car',
  templateUrl: './book-a-car.component.html',
  styleUrl: './book-a-car.component.scss',
})
export class BookACarComponent implements OnInit {
  isSpinning: boolean = false;
  id: number = this.activatedRoute.snapshot.params['id'];
  car: any;
  bidForm: FormGroup;
  constructor(
    private customerService: CustomerService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private router: Router,
    private messageService: NzMessageService
  ) {}
  ngOnInit(): void {
    this.bidForm = this.fb.group({
      price: [null, Validators.required],
    });

    this.getCar();
  }

  getCar() {
    this.customerService.getCarById(this.id).subscribe((res) => {
      console.log(res);
      this.car = res;
    });
  }

  bidACar(formData: any) {
    this.isSpinning = true;
    const obj = {
      price: formData.price,
      carId: this.id,
    };
    this.customerService.addBidACar(obj).subscribe(
      (result) => {
        this.isSpinning = false;
        this.messageService.success('Bid submitted successfully', {
          nzDuration: 5000,
        });
        this.router.navigateByUrl('/customer/dashboard');
      },
      (err) => {
        this.messageService.error('Something went wrong', { nzDuration: 5000 });
      }
    );
  }
}
