import { UserService } from './../../service/user.service';
import { AuthenticationService } from './../../service/authentication.service';
import { Component, OnInit } from '@angular/core';
import { Simplante } from 'src/app/model/model.simplante';
import { Category } from 'src/app/model/model.category';
import { SimplantesService } from 'src/app/service/simplantes.service';
import { CategoryService } from 'src/app/service/category.service';
import { Observable } from 'rxjs';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-user-simplantes',
  templateUrl: './user-simplantes.component.html',
  styleUrls: ['./user-simplantes.component.css']
})
export class UserSimplantesComponent implements OnInit {


  public simplantes: Simplante[];
  public categories: Category;
  public mode = 'list';
  public currentSimplante = {} as Simplante;

  constructor(
    private simplantesService: SimplantesService,
    private catService: CategoryService,
    private authService: AuthenticationService,
    private route: ActivatedRoute) { }

  ngOnInit() { this.loadSimplante(); }

  loadSimplante() {
    this.simplantesService.getSimplanteByUser().subscribe(
      data => {this.simplantes = data; console.log(data); },
      error => {console.log(error); });
      }

  GetSimplanteId(id: number) {
    this.getCategories();
    this.mode = 'edit-cat';
    console.log(id);
    this.simplantesService.getSimplante(id).subscribe(data => {this.currentSimplante = data; console.log(data); },
      err => {console.log(err); });
  }

  newSimplante() {
    this.getCategories();
    this.mode = 'new-cat';
  }

  saveSimplante(data: any) {
    this.simplantesService.saveSimplante(data).subscribe(
      _data => {this.loadSimplante();
      this.mode = 'list'; },
      err => {console.log(err); }
    );
  }

  editSimplante(simplante: Simplante) {
    console.log(simplante);
    this.simplantesService.updateSimplante(this.currentSimplante.id, simplante).subscribe(
      _data => {this.loadSimplante(); this.mode = 'list'; },
      err => {console.log(err); });
  }

  deleteSimplante(id: number) {
    const confirmation = confirm('Etes vous sûre de vouloir supprimer le post ?');
    if (!confirmation) { return; }
    this.simplantesService.deleteSimplante(id)
    .subscribe(() => { console.log(`Category with id ${id} deleted`);
    this.loadSimplante(); },
    (err) => {
      return console.log(err);
    });
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
