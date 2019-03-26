import { HttpClient } from '@angular/common/http';
import {Injectable} from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Config } from '../config';


@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {
LOGIN_URL: string;
jwt: string;
username: string;
roles: Array<string>;

    constructor(private http: HttpClient) {
        this.LOGIN_URL = Config.LOGIN_URL;
    }

    login(data: string) {
        return this.http.post(this.LOGIN_URL, data, {observe: 'response'});
    }

    saveToken(jwt: string) {
        localStorage.setItem('token', jwt);
        this.jwt = jwt;
        this.parseJWT();
        console.log('JWt: ', this.jwt);
        console.log('Roles: ', this.roles);
        console.log('Username: ', this.username);
      }


    parseJWT() {
        const jwtHelper = new JwtHelperService();
        const objJWT = jwtHelper.decodeToken(this.jwt);
        this.username = objJWT.sub;
        this.roles = objJWT.authorities;
    }

    isAdmin() {
        return this.roles.indexOf('ADMIN') >= 0;
    }
    isUser() {
        return this.roles.indexOf('USER') >= 0;
    }

    isAuthenticated() {
        return this.roles;
    }

    loadToken() {
        this.jwt = localStorage.getItem('token');
        this.parseJWT();
    }

    logout() {
        localStorage.removeItem('token');
        this.jwt = undefined;
        this.username = undefined;
        this.roles = undefined;
    }

}
