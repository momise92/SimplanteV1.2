import { CategoryService } from './../../service/category.service';
import { SimplantesService } from './../../service/simplantes.service';
import { Component, OnInit } from '@angular/core';
import { Simplante } from 'src/app/model/model.simplante';
import { Category } from 'src/app/model/model.category';

@Component({
  selector: 'app-admin-simplantes',
  templateUrl: './admin-simplantes.component.html',
  styleUrls: ['./admin-simplantes.component.css']
})
export class AdminSimplantesComponent implements OnInit {

  public simplantes: Simplante[];
  public categories: Category;
  public mode = 'list';
  public currentSimplante = {} as Simplante;

  constructor( private simplantesService: SimplantesService, private catService: CategoryService) { }

  ngOnInit() { this.loadSimplante(); }

  loadSimplante() {
    this.simplantesService.getAllSimplantes().subscribe(
      data => {this.simplantes = data; console.log(data); },
      err => {console.log(err); });
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
