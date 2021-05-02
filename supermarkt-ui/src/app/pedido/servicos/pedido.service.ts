import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Pedido } from '../modelos/pedido';
import { SupermercadoComAvaliacao } from '../modelos/supermercado-com-avaliacao';


@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  private API = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getSupermercadosComAvaliacao(): Observable<SupermercadoComAvaliacao[]> {
    //return this.http.get<SupermercadoComAvaliacao[]>(`${this.API}/pedidos/supermercados-avaliadoss`);
    return this.http.get<SupermercadoComAvaliacao[]>(`${this.API}/pedidos/supermercados-avaliados`);
  }

  getSupermercadoComAvaliacaoPorId(supermercadoId: string): Observable<SupermercadoComAvaliacao> {
    return this.http.get<SupermercadoComAvaliacao>(`${this.API}/pedidos/supermercado-avaliado/${supermercadoId}`);
  }

  adiciona(pedido: Pedido): Observable<Pedido> {
    return this.http.post<Pedido>(`${this.API}/pedidos`, pedido);
  }

  porId(pedidoId: number): Observable<Pedido> {
    return this.http.get<Pedido>(`${this.API}/pedidos/${pedidoId}`);
  }

  pendentes(supermercadoId: number): Observable<Pedido[]> {
    return this.http.get<Pedido[]>(`${this.API}/parceiros/supermercados/${supermercadoId}/pedidos/pendentes`);
  }

  atualizaSituacao(pedido: Pedido): Observable<Pedido> {
    return this.http.put<Pedido>(`${this.API}/pedidos/${pedido.id}/situacao`, pedido);
  }

}
