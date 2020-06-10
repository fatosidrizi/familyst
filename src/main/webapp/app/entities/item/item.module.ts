import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FamilystSharedModule } from 'app/shared/shared.module';
import { ItemComponent } from './item.component';
import { ItemDetailComponent } from './item-detail.component';
import { ItemUpdateComponent } from './item-update.component';
import { ItemDeleteDialogComponent } from './item-delete-dialog.component';
import { itemRoute } from './item.route';

@NgModule({
  imports: [FamilystSharedModule, RouterModule.forChild(itemRoute)],
  declarations: [ItemComponent, ItemDetailComponent, ItemUpdateComponent, ItemDeleteDialogComponent],
  entryComponents: [ItemDeleteDialogComponent],
})
export class FamilystItemModule {}
