import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(

    private authService: AuthenticationService

    ) { }

  ngOnInit() {}

  onLogin(data) {
    this.authService.login(data)
    .subscribe(resp => {
      console.log(resp.headers.get('Authorization'));
      const jwt = resp.headers.get('Authorization');
      this.authService.saveToken(jwt);
    }, err => {
      console.log(err);
    });
  }

  isAdmin() {
    return this.authService.isAdmin();
  }
  isUser() {
    return this.authService.isUser();
  }

}
