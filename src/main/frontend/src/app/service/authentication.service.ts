import { HttpClient } from '@angular/common/http';
import {Injectable} from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';


@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {
host2 = 'http://localhost:8080/login';
jwt: string;
username: string;
roles: Array<string>;
    constructor(private http: HttpClient) {
    }

    login(data) {
        return this.http.post(this.host2, data, {observe: 'response'});
    }

    saveToken(jwt: string): any {
        localStorage.setItem('token', jwt);
        this.jwt = jwt;
        this.parseJWT();
      }


    parseJWT() {
        const jwtHelper = new JwtHelperService();
        const objJWT = jwtHelper.decodeToken(this.jwt);
        this.username = objJWT.obj;
        this.roles = objJWT.authorities;
    }

    isAdmin() {
        return this.roles.indexOf('ROLE_ADMIN') >= 0;
    }
    isUser() {
        return this.roles.indexOf('ROLE_USER') >= 0;
    }

    isAuthenticated() {
        return this.roles;
    }

}
