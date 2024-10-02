import { Injectable } from '@angular/core';

const TOKEN = 'token';
const USER = 'user';
@Injectable({
  providedIn: 'root',
})
export class StorageService {
  constructor() {}

  static saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  static saveUser(user: any): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getToken(): string {
    return window.localStorage.getItem(TOKEN);
  }

  static getUser(): any {
    return JSON.parse(localStorage.getItem(USER));
  }

  static getUserRole(): string {
    const user = this.getUser();
    return user == null ? '' : user.role;
  }

  static isAdminLoggedIn(): boolean {
    if (this.getToken() === null) return false;

    const role: string = this.getUserRole();
    return role === 'ADMIN';
  }

  static isCustomerLoggedIn(): boolean {
    if (this.getToken() === null) return false;

    const role: string = this.getUserRole();
    return role === 'CUSTOMER';
  }

  static hasToken() {
    return this.getToken() === null ? false : true;
  }

  static getUserId(): string {
    return this.getUser() == null ? '' : this.getUser().id;
  }

  static signOut(): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }
}
