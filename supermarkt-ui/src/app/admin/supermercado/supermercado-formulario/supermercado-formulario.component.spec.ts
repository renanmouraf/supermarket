import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupermercadoFormularioComponent } from './supermercado-formulario.component';

describe('SupermercadoFormularioComponent', () => {
  let component: SupermercadoFormularioComponent;
  let fixture: ComponentFixture<SupermercadoFormularioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupermercadoFormularioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SupermercadoFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
