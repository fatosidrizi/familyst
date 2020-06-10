import { IItem } from 'app/shared/model/item.model';

export interface IShoppingList {
  id?: number;
  name?: string;
  sortOrder?: number;
  items?: IItem[];
  userLogin?: string;
  userId?: number;
}

export class ShoppingList implements IShoppingList {
  constructor(
    public id?: number,
    public name?: string,
    public sortOrder?: number,
    public items?: IItem[],
    public userLogin?: string,
    public userId?: number
  ) {}
}
