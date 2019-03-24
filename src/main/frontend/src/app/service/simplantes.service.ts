import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, tap, catchError} from 'rxjs/operators';
import {Simplante} from '../model/model.simplante';
import {Identifiers} from '@angular/compiler';
import {Observable, of} from 'rxjs';
import { Config } from '../config';

@Injectable({
    providedIn: 'root',
})
export class SimplantesService {
    API_URL: string;

    constructor(private http: HttpClient) {
        this.API_URL = Config.API_URL;
    }

    getSimplante(id: number | string) {
        return this.getAllSimplantes().pipe(
            // (+) before `id` turns the string into a number
            map((simplantes: Simplante[]) =>
                simplantes.find(simplante => simplante.id === +id)
            )
        );
    }

    getAllSimplantes(): Observable<Simplante[]> {
        return this.http.get<Simplante[]>(this.API_URL + '/posts').pipe(resp => resp);
    }

    saveSimplante(simplante: Simplante) {
        return this.http.post<Simplante[]>(this.API_URL + '/posts', simplante).pipe(resp => resp);
    }

    getSimplanteByCategories(id: number): Observable<Simplante[]> {
        return this.http.get<Simplante[]>(`${this.API_URL}/categories/${id}/posts`).pipe(resp => resp);
    }
}
