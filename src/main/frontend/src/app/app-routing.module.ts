import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SimplantesComponent } from './simplantes/simplantes.component';
import { HomeComponent } from './home/home.component';
import { AddSimplanteComponent } from './add-simplante/add-simplante.component';
import { OneSimplanteComponent } from './one-simplante/one-simplante.component';

const routes: Routes = [
  { path: 'simplante', component: SimplantesComponent },
  // { path: '', component: HomeComponent },
  { path: 'ajouter-simplante', component: AddSimplanteComponent },
  { path: 'simplante/:id', component: OneSimplanteComponent },
  // { path: '', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
