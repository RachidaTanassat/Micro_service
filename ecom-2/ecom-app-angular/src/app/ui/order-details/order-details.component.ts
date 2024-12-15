import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.css'
})
export class OrderDetailsComponent implements OnInit{
  orderId: string;
  orderDetails: any;
  constructor(private route:ActivatedRoute,
              private http: HttpClient) {
    this.orderId = this.route.snapshot.params['id'];
  }
  ngOnInit(): void {
    this.http.get("http://localhost:8088/api/orders/"+this.orderId).subscribe({
      next: value => {
        this.orderDetails = value
      },
      error: err => {
        console.log(err);
      }
    })

  }

  getTotal(orderDetails: any) {
    let total = 0;
    for(let pi of orderDetails.productItems){
      total = total + (pi.price * pi.quantity);
    }

    return total;

  }
}
