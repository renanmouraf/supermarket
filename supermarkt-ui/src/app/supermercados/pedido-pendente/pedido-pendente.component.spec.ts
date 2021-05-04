import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PedidoPendenteComponent } from './pedido-pendente.component';

describe('PedidoPendenteComponent', () => {
  let component: PedidoPendenteComponent;
  let fixture: ComponentFixture<PedidoPendenteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PedidoPendenteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PedidoPendenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
