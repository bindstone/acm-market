import {Component, OnInit} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})

// tag::asciidoc[]
export class NotificationComponent implements OnInit {

  constructor(private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
  }

  public error(message: string) {
    this.snackBar.open(message, undefined, {
      horizontalPosition: 'center',
      verticalPosition: 'top',
      panelClass: 'snackbar-error',
      duration: 5000
    })
  }

  public ok(message: string) {
    this.snackBar.open(message, undefined, {
      horizontalPosition: 'center',
      verticalPosition: 'top',
      panelClass: 'snackbar-ok',
      duration: 5000
    })
  }
}
// end::asciidoc[]
