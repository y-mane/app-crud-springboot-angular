import { TestBed } from '@angular/core/testing';

import { DepartementService } from './departement.service';
import {NO_ERRORS_SCHEMA} from "@angular/core";
import { HttpClientModule } from '@angular/common/http';


describe('DepartementService', () => {
  let service: DepartementService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      schemas : [NO_ERRORS_SCHEMA ],
      imports: [HttpClientModule]
    });
    service = TestBed.inject(DepartementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
