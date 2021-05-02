import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Autenticacao } from '../modelos/autenticacao';
import { Usuario } from '../modelos/usuario';


@Injectable({
  providedIn: 'root'
})
export class AutenticacaoService {

  private API = environment.baseUrl + '/autenticacao';
  private currentUserSubject: BehaviorSubject<Autenticacao>;
  public currentUser: Observable<Autenticacao>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<Autenticacao>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): Autenticacao {
    return this.currentUserSubject.value;
  }

  hasRole(roles: string[]): boolean {
    if (this.currentUserValue && this.currentUserValue.roles) {
      for (const role of roles) {
        if (this.currentUserValue.roles.includes(role)) {
          return true;
        }
      }
    }
    return false;
  }

  login(loginInfo: Usuario): Observable<Autenticacao> {
    return this.http.post(`${this.API}`, loginInfo)
      .pipe(map((authData: Autenticacao) => {
        if (authData && authData.token) {
          localStorage.setItem('currentUser', JSON.stringify(authData));
          this.currentUserSubject.next(authData);
        }
        return authData;
      }));
  }

  logout(): void {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(undefined);
  }

  registraParceiro(userInfo: Usuario): Observable<number> {
    return this.http.post<number>(`${this.API}/supermercado`, userInfo);
  }

}
