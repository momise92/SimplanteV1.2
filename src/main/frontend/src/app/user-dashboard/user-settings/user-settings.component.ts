import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { AuthenticationService } from 'src/app/service/authentication.service';

@Component({
  selector: 'app-user-settings',
  templateUrl: './user-settings.component.html',
  styleUrls: ['./user-settings.component.css']
})
export class UserSettingsComponent implements OnInit {
currentUser: any;

  constructor(private userService: UserService,
    private authService: AuthenticationService) { }

  ngOnInit() {this.getCurrentUser();
  }

  getCurrentUser() {
    if (this.isAuthenticated()) {
        this.userService.getCurrentUser().subscribe(
            data => {this.currentUser = data; },
            err => {console.log(err); }
        );
    }
}

isAuthenticated() {
  return this.authService.isAuthenticated();
}

}
