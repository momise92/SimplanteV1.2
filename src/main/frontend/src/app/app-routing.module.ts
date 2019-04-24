import { AdminUsersComponent } from './admin-dashboard/admin-users/admin-users.component';
import { AuthGuard } from './auth.guard';
import { RegisterComponent } from './register/register.component';
import { AdminSimplantesComponent } from './admin-dashboard/admin-simplantes/admin-simplantes.component';
import { LoginComponent } from './login/login.component';
import {OneSimplanteComponent} from './one-simplante/one-simplante.component';
import {SimplantesByCategorieComponent} from './simplantes-by-categorie/simplantes-by-categorie.component';
import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SimplantesComponent} from './simplantes/simplantes.component';
import {AddSimplanteComponent} from './add-simplante/add-simplante.component';
import { AdminCategoriesComponent } from './admin-dashboard/admin-categories/admin-categories.component';
import { HomeComponent } from './home/home.component';
import { UserSimplantesComponent } from './user-dashboard/user-simplantes/user-simplantes.component';
import { UserSettingsComponent } from './user-dashboard/user-settings/user-settings.component';

const routes: Routes = [
    { path: '', component: HomeComponent,
    children: [
        {
            path: '',
            component: SimplantesComponent
        },
        {
            path: 'category/:id/simplante',
            component: SimplantesByCategorieComponent

        },
        {
            path: 'simplante/:id',
             component: OneSimplanteComponent
        },
    ]
    },

    {path: 'ajouter-simplante', component: AddSimplanteComponent},
    {path: 'login', component: LoginComponent},

    {
        path: 'adminCategories', component: AdminCategoriesComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'adminSimplantes', component: AdminSimplantesComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'adminUsers', component: AdminUsersComponent,
        canActivate: [AuthGuard]
    },
    {path: 'inscription', component: RegisterComponent},

    {
        path: 'user/:username/simplante', component: UserSimplantesComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'settings', component: UserSettingsComponent,
        canActivate: [AuthGuard]
    },

    { path: '', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
