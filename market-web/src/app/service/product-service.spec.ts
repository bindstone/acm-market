import {TestBed} from '@angular/core/testing';

import {ProductService} from './product-service';
import {HttpClient, HttpHandler} from "@angular/common/http";

describe('ProductService', () => {
  let service: ProductService;

  beforeEach(() => {
    TestBed.configureTestingModule({providers: [HttpClient, HttpHandler]});
    service = TestBed.inject(ProductService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
