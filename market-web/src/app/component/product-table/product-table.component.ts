import {Component, OnDestroy, OnInit} from '@angular/core';
import {Product} from "../../model/product";
import {MatTableDataSource} from "@angular/material/table";
import {Observable, Subscription} from "rxjs";
import {ProductService} from "../../service/product-service";
import {MatDialog} from "@angular/material/dialog";
import {ProductDialogComponent} from "../product-dialog/product-dialog.component";
import {NotificationComponent} from "../notification/notification.component";

@Component({
  selector: 'app-product-table',
  templateUrl: './product-table.component.html',
  styleUrls: ['./product-table.component.css'],
  providers: [NotificationComponent]
})
// tag::asciidoc[]
export class ProductTableComponent implements OnInit, OnDestroy {
  displayedColumns: string[] = ['name', 'price', 'currency', 'priceEur', 'action'];
  dataSource: MatTableDataSource<Product>;
  private productSubscription: Subscription | undefined;
  private productObservable: Observable<Product[]> | undefined;
  private products: Product[];

  constructor(private productService: ProductService, private notification: NotificationComponent, public productDialog: MatDialog) {
    this.products = [];
    this.dataSource = new MatTableDataSource<Product>(this.products);
  }

  ngOnInit(): void {
    this.refresh();
  }

  public refresh() {
    this.productObservable = this.productService.getProductList();
    this.productSubscription = this.productObservable.subscribe(value => {
      this.products = value;
      this.dataSource = new MatTableDataSource<Product>(this.products);
    })
  }

  delete(product: Product) {
    this.productService.deleteProduct(product.id).subscribe(x => this.refresh());
  }

  edit(product: Product) {
    this.productDialog.open(ProductDialogComponent, {
      width: '600px',
      height: '400px',
      data: product
    }).afterClosed().subscribe(product => {
      if (product != null && product != "") {
        this.productService.updateProduct(product)
          .subscribe(result => this.refresh(),
            error => {
              if (error && error.error) {
                this.notification.error(error.error);
              } else {
                this.notification.error("Error adding Product, please try again");
              }
            });
      }
    });
  }

  ngOnDestroy(): void {
    if (this.productSubscription) {
      this.productSubscription.unsubscribe();
    }
  }
}
// end::asciidoc[]
