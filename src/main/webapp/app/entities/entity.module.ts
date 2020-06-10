import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'item',
        loadChildren: () => import('./item/item.module').then(m => m.FamilystItemModule),
      },
      {
        path: 'shopping-list',
        loadChildren: () => import('./shopping-list/shopping-list.module').then(m => m.FamilystShoppingListModule),
      },
      {
        path: 'recipe',
        loadChildren: () => import('./recipe/recipe.module').then(m => m.FamilystRecipeModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class FamilystEntityModule {}
