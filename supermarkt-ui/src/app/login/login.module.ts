import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { LoginComponent } from './login/login.component';
import { LoginRoutingModule } from './login-routing.module';

@NgModule({
declarations: [LoginComponent],
imports: [
  CommonModule,
  LoginRoutingModule,
  FormsModule,
  InputTextModule,
  ButtonModule
]
})
export class LoginModule { }