import { TestBed } from '@angular/core/testing';

import { AvaliacoesService } from './avaliacoes.service';

describe('AvaliacoesService', () => {
  let service: AvaliacoesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AvaliacoesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
