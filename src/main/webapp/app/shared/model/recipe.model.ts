import { IItem } from 'app/shared/model/item.model';

export interface IRecipe {
  id?: number;
  name?: string;
  description?: string;
  imageContentType?: string;
  image?: any;
  sortOrder?: number;
  items?: IItem[];
  userLogin?: string;
  userId?: number;
}

export class Recipe implements IRecipe {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public imageContentType?: string,
    public image?: any,
    public sortOrder?: number,
    public items?: IItem[],
    public userLogin?: string,
    public userId?: number
  ) {}
}
