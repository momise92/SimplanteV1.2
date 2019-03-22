import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {SimplantesService} from '../service/simplantes.service';
import {Simplante} from '../model/model.simplante';

@Component({
    selector: 'app-simplantes',
    templateUrl: './simplantes.component.html',
    styleUrls: ['./simplantes.component.css']
})
export class SimplantesComponent implements OnInit {
    pagesSimplante: Simplante[];

    title = 'Simplante';

    constructor(
        private http: HttpClient,
        private simplantesService: SimplantesService
    ) {
    }

    ngOnInit() {
        this.getAllSimplante();
    }

    getAllSimplante() {
        this.simplantesService.getAllSimplantes().subscribe(
            data => {
                this.pagesSimplante = data;
            },
            error => {
                console.log(error);
            }
        );
    }
}
