import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';


import { PersonneService } from './personne.service';
import {NO_ERRORS_SCHEMA} from "@angular/core";

describe('PersonneService', () => {
  let service: PersonneService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      schemas : [NO_ERRORS_SCHEMA ],
      imports: [HttpClientModule]
    });
    service = TestBed.inject(PersonneService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
