import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { AdminRoutingModule } from './admin-routing.module';


@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    SharedModule
   ]
})
export class AdminModule { }
