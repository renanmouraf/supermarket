import { TestBed } from '@angular/core/testing';

import { AutorizacaoGuard } from './autorizacao.guard';

describe('AutorizacaoGuard', () => {
  let guard: AutorizacaoGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AutorizacaoGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
