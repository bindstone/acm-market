import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {Product} from "../model/product";

// tag::asciidoc[]
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  baseUrl = "http://127.0.0.1:8080/api/v1/product/"

  constructor(private http: HttpClient) {
  }

  public getProductList(): Observable<any> {
    return this.http.get(this.baseUrl)
      .pipe(
        map(res => (res as Array<Product>)
        )
      );
  }

  public deleteProduct(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}${id}`);
  }

  addProduct(product: Product) {
    return this.http.post(this.baseUrl, product);
  }

  updateProduct(product: Product) {
    return this.http.put(this.baseUrl, product);
  }
}
// end::asciidoc[]
