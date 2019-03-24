import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FooterComponent} from './footer/footer.component';
import {HomeComponent} from './home/home.component';
import {NavComponent} from './nav/nav.component';
import {SimplantesComponent} from './simplantes/simplantes.component';
import {AddSimplanteComponent} from './add-simplante/add-simplante.component';
import {OneSimplanteComponent} from './one-simplante/one-simplante.component';
import {SidebarComponent} from './sidebar/sidebar.component';
import {CreatePostComponent} from './create-post/create-post.component';
import {SimplantesByCategorieComponent} from './simplantes-by-categorie/simplantes-by-categorie.component';
import { LoginComponent } from './login/login.component';
import { AdminCategoriesComponent } from './admin-categories/admin-categories.component';
import { AdminUsersComponent } from './admin-users/admin-users.component';
import { AdminSimplantesComponent } from './admin-simplantes/admin-simplantes.component';
import { AdminCommentsComponent } from './admin-comments/admin-comments.component';

@NgModule({
    declarations: [
        AppComponent,
        SimplantesComponent,
        NavComponent,
        HomeComponent,
        FooterComponent,
        AddSimplanteComponent,
        OneSimplanteComponent,
        SidebarComponent,
        CreatePostComponent,
        SimplantesByCategorieComponent,
        LoginComponent,
        AdminCategoriesComponent,
        AdminUsersComponent,
        AdminSimplantesComponent,
        AdminCommentsComponent
    ],
    imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
