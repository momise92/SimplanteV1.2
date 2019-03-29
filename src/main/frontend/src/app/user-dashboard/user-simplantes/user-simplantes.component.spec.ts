import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserSimplantesComponent } from './user-simplantes.component';

describe('UserSimplantesComponent', () => {
  let component: UserSimplantesComponent;
  let fixture: ComponentFixture<UserSimplantesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserSimplantesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserSimplantesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
