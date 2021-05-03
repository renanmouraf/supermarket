import { TestBed } from '@angular/core/testing';

import { ErrosService } from './erros.service';

describe('ErrosService', () => {
  let service: ErrosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ErrosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
