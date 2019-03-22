import {SimplantesService} from './../service/simplantes.service';
import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Simplante} from '../model/model.simplante';
import {ActivatedRoute, Router, NavigationStart, NavigationEnd} from '@angular/router';

@Component({
    selector: 'app-simplantes-by-categorie',
    templateUrl: './simplantes-by-categorie.component.html',
    styleUrls: ['./simplantes-by-categorie.component.css']
})
export class SimplantesByCategorieComponent implements OnInit {
    simplante;

    constructor(private simplanteService: SimplantesService, private route: ActivatedRoute, private router: Router) {
        router.events.subscribe(event => {
            if (event instanceof NavigationEnd) {
                this.getSimplante();
            }
        });
    }

    ngOnInit() {
        this.getSimplante();
    }

    getSimplante() {
        this.simplanteService.getSimplanteByCategories(
            this.route.snapshot.params['id']).subscribe(data => {
                console.log(data);
                this.simplante = data;
            },
            error => {
                console.log(error);
            }
        );
    }
}
