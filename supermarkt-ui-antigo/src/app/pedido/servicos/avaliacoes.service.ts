import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Avaliacao } from '../modelos/avaliacao';


@Injectable({
  providedIn: 'root'
})
export class AvaliacoesService {

  private API = environment.baseUrl + '/supermercados';

  constructor(
    private http: HttpClient
  ) { }

  porIdDoSupermercado(supermercadoId: string): Observable<Avaliacao[]> {
    return this.http.get<Avaliacao[]>(`${this.API}/${supermercadoId}/avaliacoes`);
  }

  salva(avaliacao: Avaliacao, supermercadoId: number): Observable<Avaliacao> {
    return this.http.post<Avaliacao>(`${this.API}/${supermercadoId}/avaliacoes`, avaliacao);
  }

}
