import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  categoriesUrl = 'http://localhost:8080/api/categories';

  constructor(private http: HttpClient) {}

  getCategories(): Observable<any> {
    return this.http.get(this.categoriesUrl).pipe(resp => resp);
  }

  // getSimplante(id: number | string) {
  // //   return this.getAllSimplantes().pipe(
  // //     // (+) before `id` turns the string into a number
  // //     map((simplantes: Simplante[]) =>
  // //       simplantes.find(simplante => simplante.id === +id)
  // //     )
  // //   );
  // // }
}
