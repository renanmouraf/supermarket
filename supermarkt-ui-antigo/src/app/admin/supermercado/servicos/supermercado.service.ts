import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TipoPagamento } from '../../tipo-pagamento/modelos/tipo-pagamento';
import { Supermercado } from '../modelos/supermercado';


@Injectable({
  providedIn: 'root'
})
export class SupermercadoService {

  private API = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getSupermercados(): Observable<Supermercado[]> {
    return this.http.get<Supermercado[]>(`${this.API}/admin/supermercados`);
  }

  getByName(nome: string): Observable<Supermercado[]> {
    return this.http.get<Supermercado[]>(`${this.API}/admin/supermercados/${nome}`);
  }

  salva(supermercado: Supermercado): Observable<Supermercado> {
    if (supermercado.id) {
      return this.http.put<Supermercado>(`${this.API}/admin/supermercados/${supermercado.id}`, supermercado);
    }
    return this.http.post<Supermercado>(`${this.API}/admin/supermercados`, supermercado);
  }

  favoritar(supermercado: Supermercado): Observable<Supermercado> {
    return this.http.put<Supermercado>(`${this.API}/supermercados/${supermercado.id}/favoritar`, supermercado);
    //return this.http.put<Supermercado>(`${this.API}/supermercados/${supermercado.id}/favoritar`, {aaa: 'aa'});
  }

  remove(supermercado: Supermercado): Observable<void> {
    return this.http.delete<void>(`${this.API}/admin/supermercados/${supermercado.id}`);
  }

  getSupermercadoById(id: number): Observable<Supermercado> {
    return this.http.get<Supermercado>(`${this.API}/supermercados/${id}`);
  }

  parceiroPorId(id: number): Observable<Supermercado> {
    return this.http.get<Supermercado>(`${this.API}/parceiros/supermercados/${id}`);
  }

  tiposPagamento(supermercado: Supermercado): Observable<TipoPagamento[]>  {
    return this.http.get<TipoPagamento[]>(`${this.API}/supermercados/${supermercado.id}/tipo-pagamento`);
  }
}
