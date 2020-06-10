import { Component, OnInit } from '@angular/core';
import { ShoppingListService } from 'app/entities/shopping-list/shopping-list.service';
import { IShoppingList } from 'app/shared/model/shopping-list.model';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  shoppingLists?: IShoppingList[];

  constructor(private shoppingListService: ShoppingListService) {}

  ngOnInit(): void {
    this.getShoppingList();
  }

  getShoppingList(): void {
    this.shoppingListService.query().subscribe((res: HttpResponse<IShoppingList[]>) => (this.shoppingLists = res.body || []));
  }
}
