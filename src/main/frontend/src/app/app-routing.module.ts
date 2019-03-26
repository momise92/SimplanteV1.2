import { LoginComponent } from './login/login.component';
import {OneSimplanteComponent} from './one-simplante/one-simplante.component';
import {SimplantesByCategorieComponent} from './simplantes-by-categorie/simplantes-by-categorie.component';
import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SimplantesComponent} from './simplantes/simplantes.component';
import {AddSimplanteComponent} from './add-simplante/add-simplante.component';
import { AdminCategoriesComponent } from './admin-dashboard/admin-categories/admin-categories.component';

const routes: Routes = [
    {path: 'simplante', component: SimplantesComponent},
    // { path: '', component: HomeComponent },
    {path: 'ajouter-simplante', component: AddSimplanteComponent},
    {path: 'simplante/:id', component: OneSimplanteComponent},
    {path: 'category/:id/simplante', component: SimplantesByCategorieComponent},
    {path: 'login', component: LoginComponent},
    {path: 'adminCategories', component: AdminCategoriesComponent},
    // { path: '', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
