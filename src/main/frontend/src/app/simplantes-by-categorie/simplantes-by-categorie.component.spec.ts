import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SimplantesByCategorieComponent} from './simplantes-by-categorie.component';

describe('SimplantesByCategorieComponent', () => {
    let component: SimplantesByCategorieComponent;
    let fixture: ComponentFixture<SimplantesByCategorieComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [SimplantesByCategorieComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(SimplantesByCategorieComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
