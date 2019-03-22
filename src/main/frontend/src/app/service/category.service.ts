import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class CategoryService {

    categoriesUrl = 'http://localhost:8080/api/categories';

    constructor(private http: HttpClient) {
    }

    getCategories(): Observable<any> {
        return this.http.get(this.categoriesUrl).pipe(resp => resp);
    }
}
