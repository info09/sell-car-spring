import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-post-car',
  templateUrl: './post-car.component.html',
  styleUrl: './post-car.component.scss',
})
export class PostCarComponent implements OnInit {
  listOfBrands = [
    'Vinfast',
    'BMW',
    'AUDI',
    'KIA',
    'HUYNDAI',
    'HONDA',
    'NISSAN',
    'LEXUS',
  ];

  listOfType = ['Petrol', 'Hybrid', 'Electric'];
  listOfColor = ['Red', 'White', 'Blue', 'Black', 'Orange', 'Gray', 'Sliver'];
  listOfTransmission = ['Manual', 'Automatic'];
  postCarForm: FormGroup;

  constructor(
    private customerService: CustomerService,
    private fb: FormBuilder,
    private router: Router,
    private messageService: NzMessageService
  ) {}
  ngOnInit(): void {
    this.postCarForm = this.fb.group({
      brand: [null, Validators.required],
      name: [null, Validators.required],
      type: [null, Validators.required],
      color: [null, Validators.required],
      transmission: [null, Validators.required],
      year: [null, Validators.required],
      description: [null, Validators.required],
      price: [null, Validators.required],
    });
  }

  postCar() {
    console.log(this.postCarForm.value);
  }
}
