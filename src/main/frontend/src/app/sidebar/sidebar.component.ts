import { AuthenticationService } from './../service/authentication.service';
import {CategoryService} from './../service/category.service';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import { Category } from '../model/model.category';

@Component({
    selector: 'app-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
    categories: any;
    constructor(private catService: CategoryService, private authService: AuthenticationService, private router: Router) { }
    ngOnInit() {
        this.catService.getCategories()
            .subscribe(data => {
                this.categories = data;
            }, err => {
                console.log(err);
            });
    }

    user() {
        return this.authService.username;
    }

    postByCategorie(c: Category) {
        this.router.navigateByUrl(c.name + '/simplante');
    }

    isAuthenticated() {
        return this.authService.isAuthenticated();
    }

}
