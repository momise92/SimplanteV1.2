import { AuthenticationService } from './service/authentication.service';
import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpInterceptor } from '@angular/common/http';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(
        private authService: AuthenticationService) {}

    intercept(req: HttpRequest<any>, next: HttpHandler) {
        // Get the auth token from the service.
        const authToken = this.authService.jwt;
        
        if (!authToken) {
          return next.handle(req);
        }
        // Clone the request and replace the original headers with
        // cloned headers, updated with the authorization.
        const authReq = req.clone({
          headers: req.headers.set('Authorization', authToken)
        });
        // send cloned request with header to the next handler.
        return next.handle(authReq);
      }
}
