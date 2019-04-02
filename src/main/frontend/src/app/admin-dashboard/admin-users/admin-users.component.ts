import { UserService } from './../../service/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/model.user';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {
  users: any;
  mode = 'list';
  currentUser = {} as User;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.loadUsers();
  }


  loadUsers() {
    this.userService.getListUsers()
    .subscribe(data => {this.users = data;
    console.log(data); },
      err => {console.log(err); });
  }

  GetUserById(id: number) {
    this.mode = 'edit-user';
    console.log(id);
    this.userService.getUser(id).subscribe(data => {this.currentUser = data; console.log(data); },
      err => {console.log(err); });
  }

  newUser() {
    this.mode = 'new-user';
  }

  saveUser(data) {
    this.userService.saveUser(data).subscribe(
        _data => {this.loadUsers();
          this.mode = 'list'; },
        error => {console.log(error); });
}

  editUser(user) {
    this.userService.updateUser(this.currentUser.id, user).subscribe(
      _data => {this.loadUsers(); this.mode = 'list'; },
      err => {console.log(err); });
  }

  deleteUser(user: number) {
    const confirmation = confirm('Etes vous sûre de vouloir supprimer l\'utilisateur ?');
    if (!confirmation) { return; }
    this.userService.deleteUser(user)
    .subscribe(() => { console.log(`User with id ${user} deleted`);
    this.loadUsers(); },
    (err) => {
      return console.log(err);
    });
  }
}
