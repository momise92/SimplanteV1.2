import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Config } from '../config';
import { User } from '../model/model.user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public API_URL = Config.API_URL;

  constructor(private http: HttpClient) {}

  getListUsers(): Observable<User> {
    return this.http.get<User>(`${this.API_URL}/users`);
  }

  getUser(id: number): Observable<User> {
    return this.http.get<User>(`${this.API_URL}/users/${id}`);
  }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`${this.API_URL}/users/current`);
  }

  saveUser(user: any): Observable<any> {
    return this.http.post(this.API_URL + '/users', user);
  }

  updateUser(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.API_URL}/users/${id}`, user);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/users/${id}`);
  }
}
