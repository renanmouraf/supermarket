import { TestBed } from '@angular/core/testing';

import { SupermercadoService } from './supermercado.service';

describe('SupermercadoService', () => {
  let service: SupermercadoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SupermercadoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
