import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

// tag::asciidoc[]
@Injectable({
  providedIn: 'root'
})
export class CurrencyService {
  baseUrl = "http://127.0.0.1:8081/api/v1/exchange/";

  constructor(private http: HttpClient) {
  }

  public getCurrencyList(): Observable<any> {
    return this.http.get(this.baseUrl)
      .pipe(
        map(res => (res as Array<any>)
          .map(exchange => exchange.ccy
          )));
  }
}
// end::asciidoc[]
