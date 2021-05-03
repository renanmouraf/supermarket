import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupermercadoGradeComponent } from './supermercado-grade.component';

describe('SupermercadoGradeComponent', () => {
  let component: SupermercadoGradeComponent;
  let fixture: ComponentFixture<SupermercadoGradeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupermercadoGradeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SupermercadoGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
