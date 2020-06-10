import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard.component';
import { RouterModule } from '@angular/router';
import { DASHBOARD_ROUTE } from 'app/dashboard/dashboard.route';

@NgModule({
  declarations: [DashboardComponent],
  imports: [CommonModule, RouterModule.forChild([DASHBOARD_ROUTE])],
})
export class DashboardModule {}
