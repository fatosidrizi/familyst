import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FamilystSharedModule } from 'app/shared/shared.module';
import { ShoppingListComponent } from './shopping-list.component';
import { ShoppingListDetailComponent } from './shopping-list-detail.component';
import { ShoppingListUpdateComponent } from './shopping-list-update.component';
import { ShoppingListDeleteDialogComponent } from './shopping-list-delete-dialog.component';
import { shoppingListRoute } from './shopping-list.route';

@NgModule({
  imports: [FamilystSharedModule, RouterModule.forChild(shoppingListRoute)],
  declarations: [ShoppingListComponent, ShoppingListDetailComponent, ShoppingListUpdateComponent, ShoppingListDeleteDialogComponent],
  entryComponents: [ShoppingListDeleteDialogComponent],
})
export class FamilystShoppingListModule {}
