import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { StorageService } from '../../../../auth/services/storage/storage.service';

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
  isSpinning: boolean = false;
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;

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
    this.isSpinning = true;
    console.log(this.postCarForm.value);
    console.log(this.selectedFile);

    const formData: FormData = new FormData();
    formData.append('img', this.selectedFile);
    formData.append('brand', this.postCarForm.get('brand').value);
    formData.append('name', this.postCarForm.get('name').value);
    formData.append('type', this.postCarForm.get('type').value);
    formData.append('color', this.postCarForm.get('color').value);
    formData.append('transmission', this.postCarForm.get('transmission').value);
    formData.append('modelYear', this.postCarForm.get('year').value);
    formData.append('description', this.postCarForm.get('description').value);
    formData.append('price', this.postCarForm.get('price').value);
    formData.append('userId', StorageService.getUserId());

    this.customerService.postCar(formData).subscribe(
      (result) => {
        this.isSpinning = false;
        this.messageService.success('Car posted successfully', {
          nzDuration: 5000,
        });
        this.router.navigateByUrl('/customer/dashboard');
      },
      (err) => {
        this.isSpinning = false;
        this.messageService.error('Something went wrong', { nzDuration: 5000 });
      }
    );
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile);
  }
}
