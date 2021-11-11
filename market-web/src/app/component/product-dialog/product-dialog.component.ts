import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Product} from "../../model/product";
import {CurrencyService} from "../../service/currency.service";

@Component({
  selector: 'app-product-dialog',
  templateUrl: './product-dialog.component.html',
  styleUrls: ['./product-dialog.component.css']
})
// tag::asciidoc[]
export class ProductDialogComponent implements OnInit {
  public productForm = new FormGroup({
    //name: new FormControl(null, [Validators.required, Validators.minLength(2), Validators.maxLength(200)]),
    name: new FormControl(null, [Validators.required, Validators.maxLength(200)]),
    currency: new FormControl(null, [Validators.required]),
    price: new FormControl(null, [Validators.required, Validators.min(0.01), Validators.max(5000)])
  })

  currencies: String[];

  constructor(public dialogRef: MatDialogRef<ProductDialogComponent>,
              private currencyService: CurrencyService,
              @Inject(MAT_DIALOG_DATA) public data: Product) {
    this.currencies = ["EUR"];
  }

  public submit(form: any) {
    let product = (form.value as Product);
    if (this.data) {
      product.id = this.data.id;
      product.created = this.data.created;
      product.updated = this.data.updated;
    }
    this.dialogRef.close(product);

  }

  ngOnInit(): void {
    if (this.data != null) {
      this.productForm.setValue({
        name: this.data.name,
        currency: this.data.currency,
        price: this.data.price
      })
    }
    this.currencyService.getCurrencyList().subscribe(result => this.currencies = result.sort());
  }
}
// end::asciidoc[]
