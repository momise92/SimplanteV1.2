import { AuthenticationService } from './authentication.service';
import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Config } from '../config';
import { Category } from '../model/model.category';

@Injectable({
    providedIn: 'root'
})
export class CategoryService {
    API_URL: string;

    constructor(
        private http: HttpClient,
        private authService: AuthenticationService) {

            this.API_URL = Config.API_URL;
    }

    getCategories(): Observable<Category> {
        return this.http.get<Category>(`${this.API_URL}/categories`);
    }

    getCategory(id: number): Observable<Category> {
        return this.http.get<Category>(`${this.API_URL}/categories/${id}`);
    }

    addCategory(category: any): Observable<Category> {
        return this.http.post<Category>(`${this.API_URL}/categories`, category);
    }

    updateCategory(id: number, category: Category): Observable<Category> {
        return this.http.put<Category>(`${this.API_URL}/categories/${id}`, category);
    }

    deleteCategory(id: number): Observable<void> {
        return this.http.delete<void>(`${this.API_URL}/categories/${id}`);
    }
}
