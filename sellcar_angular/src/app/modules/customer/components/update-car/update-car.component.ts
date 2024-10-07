import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StorageService } from '../../../../auth/services/storage/storage.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrl: './update-car.component.scss',
})
export class UpdateCarComponent implements OnInit {
  id: number = this.activatedRoute.snapshot.params['id'];
  car: any;
  existingImage: string | null = null;

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
  updateCarForm: FormGroup;
  isSpinning: boolean = false;
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;
  imgChanged: boolean = false;

  constructor(
    private customerService: CustomerService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private router: Router,
    private messageService: NzMessageService
  ) {}
  ngOnInit(): void {
    this.updateCarForm = this.fb.group({
      brand: [null, Validators.required],
      name: [null, Validators.required],
      type: [null, Validators.required],
      color: [null, Validators.required],
      transmission: [null, Validators.required],
      year: [null, Validators.required],
      description: [null, Validators.required],
      price: [null, Validators.required],
    });
    this.getCar();
  }

  getCar() {
    this.customerService.getCarById(this.id).subscribe((result) => {
      console.log(result);
      this.car = result;
      this.existingImage = 'data:image/jpeg;base64,' + result.returnedImg;
      this.updateCarForm.patchValue(result);
    });
  }

  updateCar() {
    this.isSpinning = true;
    const formData: FormData = new FormData();
    formData.append('img', this.selectedFile);
    formData.append('brand', this.updateCarForm.get('brand').value);
    formData.append('name', this.updateCarForm.get('name').value);
    formData.append('type', this.updateCarForm.get('type').value);
    formData.append('color', this.updateCarForm.get('color').value);
    formData.append(
      'transmission',
      this.updateCarForm.get('transmission').value
    );
    formData.append('modelYear', this.updateCarForm.get('year').value);
    formData.append('description', this.updateCarForm.get('description').value);
    formData.append('price', this.updateCarForm.get('price').value);
    formData.append('userId', StorageService.getUserId());

    this.customerService.updateCar(this.id, formData).subscribe(
      (result) => {
        this.isSpinning = false;
        this.messageService.success('Update car successfully', {
          nzDuration: 5000,
        });
        this.router.navigateByUrl('/customer/my-cars');
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
    this.imgChanged = true;
    this.existingImage = null;
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile);
  }
}
