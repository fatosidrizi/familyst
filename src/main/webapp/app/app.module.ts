import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { FamilystSharedModule } from 'app/shared/shared.module';
import { FamilystCoreModule } from 'app/core/core.module';
import { FamilystAppRoutingModule } from './app-routing.module';
import { FamilystHomeModule } from './home/home.module';
import { FamilystEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { DashboardModule } from 'app/dashboard/dashboard.module';

@NgModule({
  imports: [
    DashboardModule,
    BrowserModule,
    FamilystSharedModule,
    FamilystCoreModule,
    FamilystHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    FamilystEntityModule,
    FamilystAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class FamilystAppModule {}
