import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SituacaoComponent } from './situacao.component';

describe('SituacaoComponent', () => {
  let component: SituacaoComponent;
  let fixture: ComponentFixture<SituacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SituacaoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SituacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
