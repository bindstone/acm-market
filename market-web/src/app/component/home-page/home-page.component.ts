import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {ProductDialogComponent} from "../product-dialog/product-dialog.component";
import {ProductService} from "../../service/product-service";
import {ProductTableComponent} from "../product-table/product-table.component";
import {NotificationComponent} from "../notification/notification.component";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  providers: [NotificationComponent]
})

// tag::asciidoc[]
export class HomePageComponent implements OnInit {

  @ViewChild(ProductTableComponent) productTable!: ProductTableComponent;

  constructor(private productService: ProductService, private notification: NotificationComponent, public productDialog: MatDialog) {
  }

  public addProduct() {
    this.productDialog.open(ProductDialogComponent, {
      width: '600px',
      height: '400px'
    }).afterClosed().subscribe(product => {
      if (product != null && product != "") {
        this.productService.addProduct(product)
          .subscribe(result => {
              this.notification.ok(`Product ${product.name} added to market`);
              this.productTable.refresh();
            },
            error => {
              if (error && error.error) {
                this.notification.error(error.error);
              } else {
                this.notification.error("Error adding Product, please try again");
              }
            })
      }
    });
  }

  ngOnInit(): void {
  }
}
// end::asciidoc[]
