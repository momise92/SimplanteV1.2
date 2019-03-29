import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Config } from '../config';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public API_URL = Config.API_URL;

  constructor(private http: HttpClient) { }

  saveUser(user: any): Observable<any> {
    return this.http.post(this.API_URL + '/users', user);
}
}
