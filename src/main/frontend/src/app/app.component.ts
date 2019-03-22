import { AuthenticationService } from './service/authentication.service';
import {Component} from '@angular/core';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'SimplanteApp';
    description = 'Raconte nous une anecdote simplante.';
    constructor(
    ) {}
}
