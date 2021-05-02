import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Pagamento } from '../modelos/pagamento';


@Injectable({
  providedIn: 'root'
})
export class PagamentoService {

  private API = environment.baseUrl + '/pagamentos';

  constructor(private http: HttpClient) {
  }

  cria(pagamento: Pagamento): Observable<Pagamento> {
    return this.http.post<Pagamento>(`${this.API}`, pagamento);
  }

  confirma(pagamento: Pagamento): Observable<Pagamento> {
    return this.http.put<Pagamento>(`${this.API}/${pagamento.id}`, undefined);
  }

  cancela(pagamento: Pagamento): Observable<void> {
    return this.http.delete<void>(`${this.API}/${pagamento.id}`);
  }

}
