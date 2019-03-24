import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSimplantesComponent } from './admin-simplantes.component';

describe('AdminSimplantesComponent', () => {
  let component: AdminSimplantesComponent;
  let fixture: ComponentFixture<AdminSimplantesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminSimplantesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminSimplantesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
