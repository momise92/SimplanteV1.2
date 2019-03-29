import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
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
import { AdminCategoriesComponent } from './admin-dashboard/admin-categories/admin-categories.component';
import { AdminUsersComponent } from './admin-dashboard/admin-users/admin-users.component';
import { AdminSimplantesComponent } from './admin-dashboard/admin-simplantes/admin-simplantes.component';
import { AdminCommentsComponent } from './admin-dashboard/admin-comments/admin-comments.component';
import { RegisterComponent } from './register/register.component';
import { AuthInterceptor } from './auth-interceptor';
import { UserSimplantesComponent } from './user-dashboard/user-simplantes/user-simplantes.component';

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
        AdminCommentsComponent,
        RegisterComponent,
        UserSimplantesComponent
    ],
    imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
    providers: [
        {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true
      }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
