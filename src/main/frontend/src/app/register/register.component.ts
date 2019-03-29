import { Router } from '@angular/router';
import { UserService } from './../service/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../model/model.user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User = new User();

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
  }
  saveUser() {
    console.log(this.user);
    this.userService.saveUser(this.user).subscribe(
        _data => {this.router.navigate(['/login']); },
        error => {console.log(error); });
}
}
