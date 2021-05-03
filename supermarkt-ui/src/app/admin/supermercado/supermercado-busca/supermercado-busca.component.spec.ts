import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupermercadoBuscaComponent } from './supermercado-busca.component';

describe('SupermercadoBuscaComponent', () => {
  let component: SupermercadoBuscaComponent;
  let fixture: ComponentFixture<SupermercadoBuscaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupermercadoBuscaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SupermercadoBuscaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
