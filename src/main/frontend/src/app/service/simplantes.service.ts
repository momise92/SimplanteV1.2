import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map, tap, catchError} from 'rxjs/operators';
import {Simplante} from '../model/model.simplante';
import {Identifiers} from '@angular/compiler';
import {Observable, of} from 'rxjs';
import { Config } from '../config';
import { AuthenticationService } from './authentication.service';

@Injectable({
    providedIn: 'root',
})
export class SimplantesService {
    API_URL = Config.API_URL;

    constructor(
        private http: HttpClient) { }

        getSimplante(id: number): Observable<any> {
            return this.http.get(`${this.API_URL}/posts/${id}`);
        }

    // getSimplante(id: number | string) {
    //     return this.getAllSimplantes().pipe(
    //         // (+) before `id` turns the string into a number
    //         map((simplantes: Simplante[]) =>
    //             simplantes.find(simplante => simplante.id === +id)
    //         ));
    // }

    getSimplanteByUser(): Observable<Simplante> {
        return this.http.get<Simplante>(this.API_URL + '/users/posts');
    }


    getAllSimplantes(): Observable<Simplante[]> {
        return this.http.get<Simplante[]>(this.API_URL + '/posts');
    }

    saveSimplante(simplante: Simplante): Observable<Simplante> {
        return this.http.post<Simplante>(this.API_URL + '/posts', simplante);
    }

    updateSimplante(id: number, simplante: Simplante): Observable<Simplante> {
        return this.http.put<Simplante>(`${this.API_URL}/posts/${id}`, simplante);
    }

    getSimplanteByCategories(id: number): Observable<Simplante> {
        return this.http.get<Simplante>(`${this.API_URL}/categories/${id}/posts`);
    }

    deleteSimplante(id: number): Observable<void> {
        return this.http.delete<void>(`${this.API_URL}/posts/${id}`);
    }
}
