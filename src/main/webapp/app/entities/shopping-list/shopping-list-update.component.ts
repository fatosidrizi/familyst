import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IShoppingList, ShoppingList } from 'app/shared/model/shopping-list.model';
import { ShoppingListService } from './shopping-list.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-shopping-list-update',
  templateUrl: './shopping-list-update.component.html',
})
export class ShoppingListUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    sortOrder: [],
    userId: [null, Validators.required],
  });

  constructor(
    protected shoppingListService: ShoppingListService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shoppingList }) => {
      this.updateForm(shoppingList);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(shoppingList: IShoppingList): void {
    this.editForm.patchValue({
      id: shoppingList.id,
      name: shoppingList.name,
      sortOrder: shoppingList.sortOrder,
      userId: shoppingList.userId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const shoppingList = this.createFromForm();
    if (shoppingList.id !== undefined) {
      this.subscribeToSaveResponse(this.shoppingListService.update(shoppingList));
    } else {
      this.subscribeToSaveResponse(this.shoppingListService.create(shoppingList));
    }
  }

  private createFromForm(): IShoppingList {
    return {
      ...new ShoppingList(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      sortOrder: this.editForm.get(['sortOrder'])!.value,
      userId: this.editForm.get(['userId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShoppingList>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IUser): any {
    return item.id;
  }
}
