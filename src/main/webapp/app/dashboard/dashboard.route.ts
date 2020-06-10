import { Route } from '@angular/router';

import { DashboardComponent } from 'app/dashboard/dashboard.component';
import { Authority } from 'app/shared/constants/authority.constants';

export const DASHBOARD_ROUTE: Route = {
  path: '',
  component: DashboardComponent,
  data: {
    authorities: [Authority.USER],
    pageTitle: 'dashboard.title',
  },
};
