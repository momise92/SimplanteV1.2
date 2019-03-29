import { CategoryService } from '../../service/category.service';
import { Component, OnInit } from '@angular/core';
import { Category } from '../../model/model.category';

@Component({
  selector: 'app-admin-categories',
  templateUrl: './admin-categories.component.html',
  styleUrls: ['./admin-categories.component.css']
})
export class AdminCategoriesComponent implements OnInit {

  categories: any;
  mode = 'list';
  currentCategory = {} as Category;

  constructor(
    private categoryService: CategoryService
  ) { }

  ngOnInit() {
    this.loadCategories();
    }

    loadCategories() {
      this.categoryService.getCategories()
      .subscribe(data => {this.categories = data;
      console.log(data); },
        err => {console.log(err); });
    }

    GetCategorybyId(id: number) {
      this.mode = 'edit-cat';
      console.log(id);
      this.categoryService.getCategory(id).subscribe(data => {this.currentCategory = data; console.log(data); },
        err => {console.log(err); });
    }

    newCategory() {
      this.mode = 'new-cat';
    }

    saveCategory(data: any) {
      this.categoryService.addCategory(data).subscribe(
        _data => {this.loadCategories();
        this.mode = 'list'; },
        err => {console.log(err); }
      );
    }

    editCategory(category) {
      this.categoryService.updateCategory(this.currentCategory.id, category).subscribe(
        _data => {this.loadCategories(); this.mode = 'list'; },
        err => {console.log(err); });
    }

    deleteCategory(cat: number) {
      const confirmation = confirm('Etes vous sûre de vouloir supprimer la catégorie ?');
      if (!confirmation) { return; }
      this.categoryService.deleteCategory(cat)
      .subscribe(() => { console.log(`Category with id ${cat} deleted`);
      this.loadCategories(); },
      (err) => {
        return console.log(err);
      });
    }
}
