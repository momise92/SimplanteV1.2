import { AppComponent } from './../app.component';
import {Component, OnInit} from '@angular/core';
import { from } from 'rxjs';
import { AuthenticationService } from '../service/authentication.service';

@Component({
    selector: 'app-nav',
    templateUrl: './nav.component.html',
    styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
    constructor(
        private authService: AuthenticationService
    ) {}

    ngOnInit() {}

    isAdmin() {
        return this.authService.isAdmin();
      }
      isUser() {
        return this.authService.isUser();
      }

      isAuthenticated() {
          return this.authService.isAuthenticated();
      }
}
