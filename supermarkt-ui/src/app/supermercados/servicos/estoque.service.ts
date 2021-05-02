import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ItemEstoque } from '../modelos/item-estoque';


@Injectable({
  providedIn: 'root'
})
export class EstoqueService {

  private API = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getByName(idSupermercado: number, nome: string): Observable<ItemEstoque[]> {
    return this.http.get<ItemEstoque[]>(`${this.API}/parceiros/supermercados/${idSupermercado}/estoque/${nome}`);
  }

  salva(idSupermercado: number, itemEstoque: ItemEstoque): Observable<ItemEstoque> {
    itemEstoque.supermercadoId = idSupermercado;
    if (itemEstoque.id) {
      return this.http.put<ItemEstoque>(`${this.API}/parceiros/supermercados/${idSupermercado}/estoque/${itemEstoque.id}`, itemEstoque);
    }
    return this.http.post<ItemEstoque>(`${this.API}/parceiros/supermercados/${idSupermercado}/estoque`, itemEstoque);
  }

  remove(idSupermercado: number, idItemEstoque: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/parceiros/supermercados/${idSupermercado}/estoque/${idItemEstoque}`);
  }

  getItemEstoqueById(idSupermercado: number, idItemEstoque: number): Observable<ItemEstoque> {
    return this.http.get<ItemEstoque>(`${this.API}/parceiros/supermercados/${idSupermercado}/estoque/${idItemEstoque}`);
  }

  estoquePorSupermercadoId(supermercadoId: number): Observable<ItemEstoque[]> {
    return this.http.get<ItemEstoque[]>(`${this.API}/supermercados/${supermercadoId}/estoque`);
  }

  detalhaEstoqueDoSupermercado(supermercadoId: number): Observable<ItemEstoque[]> {
    return this.http.get<ItemEstoque[]>(`${this.API}/parceiros/supermercados/${supermercadoId}/estoque/detalha`);
  }

}
