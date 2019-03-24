import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Config } from '../config';

@Injectable({
    providedIn: 'root'
})
export class CategoryService {
    API_URL: string;

    constructor(private http: HttpClient) {
        this.API_URL = Config.API_URL;
    }

    getCategories(): Observable<any> {
        return this.http.get(`${this.API_URL}/categories`).pipe(resp => resp);
    }
}
