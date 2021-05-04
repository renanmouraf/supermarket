import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaSupermercadosComponent } from './lista-supermercados.component';

describe('ListaSupermercadosComponent', () => {
  let component: ListaSupermercadosComponent;
  let fixture: ComponentFixture<ListaSupermercadosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaSupermercadosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaSupermercadosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
