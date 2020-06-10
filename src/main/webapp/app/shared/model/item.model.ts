export interface IItem {
  id?: number;
  name?: string;
  active?: boolean;
  imageContentType?: string;
  image?: any;
  sortOrder?: number;
  shoppingListName?: string;
  shoppingListId?: number;
  recipeName?: string;
  recipeId?: number;
}

export class Item implements IItem {
  constructor(
    public id?: number,
    public name?: string,
    public active?: boolean,
    public imageContentType?: string,
    public image?: any,
    public sortOrder?: number,
    public shoppingListName?: string,
    public shoppingListId?: number,
    public recipeName?: string,
    public recipeId?: number
  ) {
    this.active = this.active || false;
  }
}
