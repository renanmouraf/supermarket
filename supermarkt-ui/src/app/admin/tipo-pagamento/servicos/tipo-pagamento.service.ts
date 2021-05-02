import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { FormaPagamento } from '../modelos/forma-pagamento';
import { TipoPagamento } from '../modelos/tipo-pagamento';


@Injectable({
  providedIn: 'root'
})
export class TipoPagamentoService {

  private API = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getTiposPagamento(): Observable<TipoPagamento[]> {
    return this.http.get<TipoPagamento[]>(`${this.API}/tipo-pagamento`);
  }

  getFormas(): Observable<FormaPagamento[]> {
    return this.http.get<FormaPagamento[]>(`${this.API}/admin/tipo-pagamento/formas`);
  }

  salva(tipoPagamento: TipoPagamento): Observable<TipoPagamento> {
    if (tipoPagamento.id) {
      return this.http.put<TipoPagamento>(`${this.API}/admin/tipo-pagamento/${tipoPagamento.id}`, tipoPagamento);
    }
    return this.http.post<TipoPagamento>(`${this.API}/admin/tipo-pagamento`, tipoPagamento);
  }

  remove(tipoPagamento: TipoPagamento): Observable<void> {
    return this.http.delete<void>(`${this.API}/admin/tipo-pagamento/${tipoPagamento.id}`);
  }

  getByName(nome: string): Observable<TipoPagamento[]> {
    return this.http.get<TipoPagamento[]>(`${this.API}/tipo-pagamento/${nome}`);
  }

  getTipoPagamentoById(id: number): Observable<TipoPagamento> {
    return this.http.get<TipoPagamento>(`${this.API}/admin/tipo-pagamento/${id}`);
  }

}
