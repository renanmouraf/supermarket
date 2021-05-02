import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../../shared/modelos/usuario';
import { AutenticacaoService } from '../../shared/services/autenticacao.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginInfo: Usuario = new Usuario('', '');

  constructor(private router: Router,
              private autenticacaoService: AutenticacaoService) { }

  efetuaLogin(): void {
    this.autenticacaoService.login(this.loginInfo)
      .subscribe(() => this.router.navigate(['']));
  }

}
