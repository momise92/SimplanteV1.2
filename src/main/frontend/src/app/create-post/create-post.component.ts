import { AuthenticationService } from './../service/authentication.service';
import {Component, OnInit} from '@angular/core';
import {Simplante} from '../model/model.simplante';
import {SimplantesService} from '../service/simplantes.service';
import {CategoryService} from '../service/category.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-create-post',
    templateUrl: './create-post.component.html',
    styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {
    simplante: Simplante = new Simplante();
    categories: any;


    constructor(private simplantesService: SimplantesService,
         private catService: CategoryService,
          private router: Router,
          private authService: AuthenticationService) {
    }

    ngOnInit() {
        this.getCategories();
    }

    isAuthenticated() {
        return this.authService.isAuthenticated();
    }

    saveSimplante() {
        this.simplantesService.saveSimplante(this.simplante).subscribe(
            data => {
                this.router.navigate(['/simplante']);
                location.reload(); },
            error => {console.log(error); });
    }

    getCategories() {
        this.catService.getCategories()
            .subscribe(data => {
                this.categories = data;
                console.log(data);
            }, err => {
                console.log(err);
            });
    }

}
