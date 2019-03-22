import {CategoryService} from './../service/category.service';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
    selector: 'app-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

    constructor(private catService: CategoryService, private router: Router) {
    }

    categories: any;

    ngOnInit() {
        this.catService.getCategories()
            .subscribe(data => {
                this.categories = data;
            }, err => {
                console.log(err);
            });
    }

    postByCategorie(c) {
        this.router.navigateByUrl(c.name + '/simplante');
    }

}
