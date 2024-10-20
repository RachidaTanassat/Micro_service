import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit{

  orders: any;
  customerId!: number


  constructor(private http:HttpClient, private route: ActivatedRoute, private router: Router) {
   this.customerId=route.snapshot.params['customerId']
  }
  ngOnInit(): void {
    this.http.get("http://localhost:9999/order-service/orders/search/byCustomerId?customerId=1&projection=fullOrder")
      .subscribe({
        next: (data)=>{
          this.orders = data;

        },
        error: (err)=>{

        }
      });
  }


  getOrderDetails(o: any) {
    this.router.navigateByUrl("/order-details/"+o.id);

  }
}
