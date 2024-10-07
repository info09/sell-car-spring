import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StorageService } from '../../../auth/services/storage/storage.service';

const BASE_URL = 'http://localhost:8080/';
@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  constructor(private http: HttpClient) {}

  postCar(formData: any): Observable<any> {
    return this.http.post(BASE_URL + 'api/customer/car', formData, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getAllCars(): Observable<any> {
    return this.http.get(BASE_URL + 'api/customer/cars', {
      headers: this.createAuthorizationHeader(),
    });
  }

  getMyCars(): Observable<any> {
    return this.http.get(
      BASE_URL + `api/customer/my-car/${StorageService.getUserId()}`,
      {
        headers: this.createAuthorizationHeader(),
      }
    );
  }

  createAuthorizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.set(
      'Authorization',
      `Bearer ${StorageService.getToken()}`
    );
  }
}
